provider "aws" {
    region = "ap-south-1"
  
}
terraform {
  backend "s3" {
    bucket = "lkmn777"
    key    = "Lock-ID"
    region = "ap-south-1"
    dynamodb_table = "thisismylife"
  }
}