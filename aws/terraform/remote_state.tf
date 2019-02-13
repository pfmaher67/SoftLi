data "terraform_remote_state" "baseref" {
  backend = "s3"
  workspace = "${terraform.workspace}"
  config {
    bucket = "com.infracog.terraform"
    key = "base"
    region = "us-east-1"
  }
}
