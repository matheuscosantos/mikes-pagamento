provider "aws" {
  region = var.region
}

# -- iam

resource "aws_iam_role" "ecs_execution_role" {
  name = "${var.name}_ecs_execution_role"
  assume_role_policy = file("iam/role/ecs_execution_role.json")
}

resource "aws_iam_policy_attachment" "ecs_execution_role_ecr_policy_attachment" {
  name       = "${var.name}_ecs_execution_role_ecr_policy_attachment"
  roles      = [aws_iam_role.ecs_execution_role.name]
  policy_arn = "arn:aws:iam::aws:policy/EC2InstanceProfileForImageBuilderECRContainerBuilds"
}

# -- task definition

data "aws_secretsmanager_secret" "db_credentials" {
  name = "mikes/db/db_credentials"
}

data "aws_secretsmanager_secret_version" "db_credentials_current" {
  secret_id = data.aws_secretsmanager_secret.db_credentials.id
}

locals {
  db_credentials = jsondecode(data.aws_secretsmanager_secret_version.db_credentials_current.secret_string)
}

resource "aws_ecs_task_definition" "ecs_task_definition" {
  family                   = var.name
  network_mode             = "awsvpc"
  execution_role_arn = aws_iam_role.ecs_execution_role.arn

  container_definitions = templatefile("container/definitions/payment_container_definitions.json", {
  })
}

# -- service

data "aws_ecs_cluster" "ecs_cluster" {
  cluster_name = "${var.cluster_name}_cluster"
}

data "aws_security_group" "security_group" {
  name  = "${var.sg_name}_security_group"
}

data "aws_lb_target_group" "lb_target_group" {
  name = "${var.tg_name}-lb-target-group"
}

resource "aws_ecs_service" "ecs_service" {
  name            = "${var.name}_service"
  cluster         = data.aws_ecs_cluster.ecs_cluster.id
  task_definition = aws_ecs_task_definition.ecs_task_definition.arn
  desired_count = 1 // desligando recursos p/ evitar cobranças

  network_configuration {
    subnets = var.subnets
    security_groups = [data.aws_security_group.security_group.id]
  }

  load_balancer {
    target_group_arn = data.aws_lb_target_group.lb_target_group.arn
    container_name   = "payment-container"
    container_port   = 8050
  }

  force_new_deployment = true

  placement_constraints {
    type = "distinctInstance"
  }

  capacity_provider_strategy {
    capacity_provider = "${var.name}_capacity_provider"
    weight            = 100
  }
}