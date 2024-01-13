terraform {
  backend "s3" {
    bucket = "mikes-terraform-state"
    key    = "payment.tfstate"
    region = "us-east-2"
    encrypt = true
  }
}