
module "vpc" {
  source = "terraform-aws-modules/vpc/aws"

  name = var.vpc_name
  cidr = var.vpc_cidr_block

  azs             = [ local.az-a,local.az-b ]
  private_subnets = var.private_subnet_cidr_block
  public_subnets  = var.public_subnets_cidr_block

  enable_nat_gateway = false
  enable_vpn_gateway = false

  tags = {
    Terraform = "true"
    Environment = "dev"
  }
}