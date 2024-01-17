variable "region" {
  type    = string
  default = "us-east-2"
}

variable "name" {
  type    = string
  default = "mikes-pagamento"
}

variable "infra_name" {
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

variable "db_name" {
  type    = string
  default = "pagamento"
}