terraform {
  backend "s3" {
    bucket = "com.infracog.terraform"
    key = "tf-state-softli-db"
    region = "us-east-1"

  }
}

resource "aws_db_subnet_group" "default" {
  name = "dbmain"
  subnet_ids = ["${data.terraform_remote_state.baseref.subnet-main-public-1-id}", "${data.terraform_remote_state.baseref.subnet-main-public-2-id}"]
}

resource "aws_db_instance" "db" {
  identifier = "softli"
  allocated_storage = 10
  storage_type = "gp2"
  engine = "mariadb"
  engine_version = "10.3.8"
  instance_class = "db.t3.medium"
  name = "SoftLi"
  username = "dbusoftli"
  password = "dbusoftlipw"
  availability_zone = "us-east-1a"
  vpc_security_group_ids = ["${data.terraform_remote_state.baseref.sg-protected-id}"]
  db_subnet_group_name = "${aws_db_subnet_group.default.name}"
  multi_az = false
  backup_retention_period = 0
  skip_final_snapshot = true
  publicly_accessible = false  # assign a public ip
}
