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

variable "sqs_payment" {
    type = string
    default = "solicitar-pagamento"
}

variable "sns_payment_status" {
    type = string
    default = "status-pagamento"
}