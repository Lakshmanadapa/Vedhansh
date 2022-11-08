provider "aws" {
    region = var.region
  
}
resource "aws_vpc" "my_vpc" {
    count = 2
    cidr_block = var.cidr_block
    tags = {
      "Name" = var.tags
    }
}
