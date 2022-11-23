variable "region" {
    type = string
  
}
variable "vpc_cidr_block" {
    type = string
  
}
variable "public_subnet_cidr_block" {
    type = list(string)
  
}
variable "private_subnet_cidr_block" {
    type = list(string)
  
}
variable "private_subnets_name_tags" {
    type = list(string)
  
}
variable "public_subnets_name_tags" {
    type = list(string)
  
}
variable "vpc_name_tags" {
    type = string
  
}
