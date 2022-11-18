provider "aws" {
    region = "ap-south-1"
  
}
resource "aws_vpc" "my_vpc" {
    cidr_block = "10.10.0.0/16"
    tags = {
      "Name" = "janu"
    }
  
}
resource "aws_security_group" "web_sg" {
    description = "security group creation"
    ingress {
        cidr_blocks = ["0.0.0.0/0"]
        description = "open http"
        from_port = 80
        protocol = "tcp"
        to_port = 80
    }
    ingress {
        cidr_blocks =["0.0.0.0/0"]
        description = "open ssh"
        from_port = 22
        protocol = "tcp"
        to_port = 22
    }
    ingress {
    cidr_blocks = ["0.0.0.0/0"]
    description = "open mysql"
    from_port = 3306
    protocol = "tcp"
    to_port = 3306
    }
    egress {
        from_port = 0
        to_port = 0
        protocol = "-1"
        cidr_blocks = ["0.0.0.0/0"]
        ipv6_cidr_blocks = ["::/0"]

    }
    tags = {
      "Name" = "my sql"
    }
  
}