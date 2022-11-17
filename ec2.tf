resource "aws_instance" "webec2" {
    ami = var.ami_id
    associate_public_ip_address = true
    instance_type = "t2.micro"
    key_name = "vedhansh"
    vpc_security_group_ids = [ aws_security_group.web_sg.id ]
    subnet_id = module.vpc.public_subnets[0]
    tags = {
        "Name" = "aws"
    }
    depends_on = [
        module.vpc,
        aws_security_group.web_sg
    ]  
}