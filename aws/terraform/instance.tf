resource "aws_key_pair" "SoftLiKey" {
  key_name = "SoftLiKey-${terraform.workspace}"
  public_key = "${file("${var.PATH_TO_PUBLIC_KEY}")}"
}

resource "aws_instance" "SoftLi" {
  ami = "ami-9887c6e7"
  instance_type = "${lookup(var.INSTANCE_TYPE, terraform.workspace)}"
  key_name = "${aws_key_pair.SoftLiKey.key_name}"

    tags {
        Name = "SoftLi-${terraform.workspace}"
    }
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
  connection {
    user = "${var.INSTANCE_USERNAME}"
    private_key = "${file("${var.PATH_TO_PRIVATE_KEY}")}"
  }

  # the VPC subnet
  subnet_id = "${aws_subnet.main-public-1.id}"

  # the security group
  vpc_security_group_ids = ["${aws_security_group.ssh-http.id}"]

}
