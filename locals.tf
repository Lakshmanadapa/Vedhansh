locals {
  az-a = format("%sa",var.region)
  az-b =format("%sb",var.region)
  mysql_port = 3306
  tcp = "tcp"
  http_port = 80
  ssh_port = 22
  anywhere = "0.0.0.0/0"
}