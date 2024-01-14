variable "region" {
  type    = string
  default = "us-east-2"
}

variable "name" {
  type    = string
  default = "mikes-pagamento"
}

variable "cluster_name" {
  type    = string
  default = "mikes"
}

variable "sg_name" {
  type    = string
  default = "mikes"
}

variable "tg_name" {
  type    = string
  default = "mikes"
}

variable "capacity_provider_name" {
  type    = string
  default = "mikes"
}

variable "subnets" {
  type    = list(string)
  default = [
    "subnet-0c9e1d22c842d362b",
    "subnet-08e43d2d7fa2c463e"
  ]
}

variable "aws_db_subnet_group_name" {
    type     = string
    default  = "mikes-payment-subnet-group"
}

variable "subnet_ids" {
  type    = list(string)
  default = [
    "subnet-0c9e1d22c842d362b",
    "subnet-08e43d2d7fa2c463e"
  ]
}

variable "db_identifier" {
    type = string
    default = "mikes-payment-db"
}

variable "db_allocated_storage" {
    type = number
    default = 20
}

variable "db_name" {
    type = string
    default = "mikespaymentdb"
}

variable "vpc_security_group_ids" {
  type    = list(string)
  default = [
    "sg-01f81ec455ea45da9",
  ]
}
variable "db_credentials_arn" {
    type = string
    default = "arn:aws:secretsmanager:us-east-2:644237782704:secret:mikes/db/db_credentials-6wQzyQ"

}