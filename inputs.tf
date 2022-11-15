variable "region" {
    type = string
  
}
variable "vpc_name" {
    type = list(string)
  
}
variable "vpc_cidr_block" {
    type = list(string)
  
}
variable "primary_subnet_cidr_block" {
    type = list(string)
  
}
variable "public_subnet_cidr_block" {
    type = list(string)
  
}
variable "primary_name_tags" {
    type = list(string)
  
}
variable "public_name_tags" {
    type = list(string)
  
}


