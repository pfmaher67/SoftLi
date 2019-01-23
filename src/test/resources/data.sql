insert into LicenseRight(id, appId, swReleaseId, qtyOwned, qtyReserved) values
 ('AB-1-MB-1', 'AB-1', 'MB-1', 32, 0)
 ,('AB-2-MB-1', 'AB-2', 'MB-1', 16, 0)
 ,('AB-2-MB-5', 'AB-2', 'MB-5', 64, 0)
 ,('AB-3-MB-3', 'AB-3', 'MB-3', 3, 0);
 

insert into LicenseModel(id, name, licenseMetricId, softwareCategoryId) values
('MB-1', 'WAS ND', 0, 0)
,('MB-2', 'RHEL', 2, 1)
,('MB-3', 'MongoDB', 2, 0)
,('MB-4', 'Qualys', 2, 1)
,('MB-5', 'Kafka', 0, 0)



