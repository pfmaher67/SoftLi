variable "PATH_TO_PRIVATE_KEY" {
  default = "~/.ssh/SoftLi"
}
variable "PATH_TO_PUBLIC_KEY" {
  default = "~/.ssh/SoftLi.pub"
}
variable "INSTANCE_USERNAME" {
  default = "centos"
}
variable "INSTANCE_TYPE" {
  type = "map"

  default = {
    dev = "t2.nano"
    stg = "t2.micro"
    prd = "t2.small"
  }
}
variable "SSH_CIDR" {
  default = "47.187.108.244/32"
}

variable "AWS_REGION" {
  default = "us-east-1"
}
variable "AWS_AZ" {
  default = "us-east-1a"
}
variable "DOMAIN" {
  default = "gnoxy.com"
}
