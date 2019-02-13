terraform {
  backend "s3" {
    bucket = "com.infracog.terraform"
    key = "tf-state-softli-server"
    region = "us-east-1"

  }
}

resource "aws_key_pair" "SoftLiKey" {
  key_name = "SoftLiKey-${terraform.workspace}"
  public_key = "${file("${var.PATH_TO_PUBLIC_KEY}")}"
}

resource "aws_instance" "SoftLi" {
  ami = "ami-9887c6e7"  #CentOS 7
  instance_type = "${lookup(var.INSTANCE_TYPE, terraform.workspace)}"
  key_name = "${aws_key_pair.SoftLiKey.key_name}"

  tags {
      Name = "SoftLi-${terraform.workspace}"
  }

  connection {
    user = "${var.INSTANCE_USERNAME}"
    private_key = "${file("${var.PATH_TO_PRIVATE_KEY}")}"
  }

  # the VPC subnet
  subnet_id = "${data.terraform_remote_state.baseref.subnet-main-public-1-id}"

  # the security group
  vpc_security_group_ids = ["${data.terraform_remote_state.baseref.sg-ssh-http-id}"]

#  provisioner "file" {
#    source = "script.sh"
#    destination = "/tmp/script.sh"
#  }
#  provisioner "remote-exec" {
#    inline = [
#      "chmod +x /tmp/script.sh",
#      "sudo /tmp/script.sh"
#    ]
#  }



}

#Route53
data "aws_route53_zone" "selected" {
  name         = "${var.DOMAIN}"
  private_zone = false
}

locals {
  serverNameBase = "softli"
  envSuffix = "-${terraform.workspace}"
  nullSuffix = ""
  serverNameSuffix = "${terraform.workspace == "prd" ? local.nullSuffix : local.envSuffix}"
}

resource "aws_route53_record" "softli" {
  zone_id = "${data.aws_route53_zone.selected.zone_id}"
  name    = "softli${local.serverNameSuffix}.${var.DOMAIN}"
  type    = "A"
  ttl     = "300"
  records = ["${aws_instance.SoftLi.public_ip}"]
}