resource "aws_vpc" "my_vpc" {
    count = 2
    cidr_block = var.vpc_cidr_block[count.index]
    tags = {
      "Name" = var.vpc_name[count.index]
    }
  
}
resource "aws_subnet" "primary_vpc_subnet" {
    count = 4
    cidr_block = var.primary_subnet_cidr_block[count.index]
    vpc_id = aws_vpc.my_vpc[0].id
    tags = {
      "Name" = var.primary_name_tags[count.index]
    }
    depends_on = [
      aws_vpc.my_vpc
    ]

  
}
resource "aws_subnet" "public_vpc_subnet" {
    count = 4
    cidr_block = var.public_subnet_cidr_block[count.index]
    vpc_id = aws_vpc.my_vpc[1].id
    tags = {
      "Name" = var.public_name_tags[count.index]
    }
    depends_on = [
      aws_vpc.my_vpc
    ]

  
}