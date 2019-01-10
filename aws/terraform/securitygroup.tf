resource "aws_security_group" "ssh-http" {
  vpc_id = "${aws_vpc.main.id}"
  name = "ssh-http"
  description = "security group that allows http traffic from anywhere and ssh from home"
  egress {
      from_port = 0
      to_port = 0
      protocol = "-1"
      cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
      from_port = 80
      to_port = 80
      protocol = "tcp"
      cidr_blocks = ["0.0.0.0/0"]
  } 

  ingress {
      from_port = 8080
      to_port = 8080
      protocol = "tcp"
      cidr_blocks = ["0.0.0.0/0"]
  } 

  ingress {
      from_port = 22
      to_port = 22
      protocol = "tcp"
      cidr_blocks = ["${var.SSH_CIDR}"]
  } 

tags {
    Name = "ssh-http"
  }
}
