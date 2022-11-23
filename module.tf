module "vpc" {
  source = "terraform-aws-modules/vpc/aws"

  name = var.vpc_name_tags
  cidr = var.vpc_cidr_block

  azs             = [ format("%sa",var.region),format("%sb",var.region) ]
  private_subnets = var.private_subnet_cidr_block
  public_subnets  = var.public_subnet_cidr_block

  enable_nat_gateway = false
  enable_vpn_gateway = false

  tags = {
    Terraform = "true"
    Environment = "dev"
  }
}