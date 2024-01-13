terraform {
  backend "s3" {
    bucket = "payment-terraform-state"
    key    = "payment.tfstate"
    region = "us-east-2"
    encrypt = true
  }
}