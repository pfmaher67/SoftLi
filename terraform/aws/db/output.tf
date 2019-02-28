output "SoftLiDB-endpoint" {
  value = "${aws_db_instance.db.endpoint}"
}

output "SoftLiDB-address" {
  value = "${aws_db_instance.db.address}"
}
