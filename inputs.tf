variable "region" {
    type = string
  
}
variable "vpc_cidr_block" {
    type = string
  
}
variable "vpc_name" {
    type = string
  
}
variable "private_subnet_cidr_block" {
    type = list(string)
  
}
variable "public_subnets_cidr_block" {
    type = list(string)
  
}
variable "ami_id" {
    type = string
  
}