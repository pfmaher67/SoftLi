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
