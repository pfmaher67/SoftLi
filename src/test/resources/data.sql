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
,('MB-5', 'Kafka', 0, 0);

--insert into SoftwareRelase(id, name, version, licenseModelId) values
--('RB-1', 'WAS ND exe', '8.1.1.13', 'MB-1')
--,('RB-2', 'WAS ND exe2', '8.1.1.14', 'MB-1')
--,('RB-3', 'RHEL 6', '6', 'MB-2')
--,('RB-4', 'RHEL 7', '7', 'MB-2')
--,('RB-5', 'Mongo', '3.6', 'MB-3')
--,('RB-6', 'Qualys', 'X', 'MB-4')
--,('RB-7', 'Kafka', 'Y', 'MB-5');

insert into Images(imageId, platform) values
('IB-1', 'AWS')
,('IB-2', 'AWS')
,('IB-3', 'AWS');


insert into manifests(image_id, swReleaseId) values
('IB-1', 'RB-1')
,('IB-1', 'RB-3')
,('IB-1', 'RB-6')
,('IB-2', 'RB-4')
,('IB-2', 'RB-6')
,('IB-2', 'RB-5')
,('IB-3', 'RB-2')
,('IB-3', 'RB-4')
,('IB-3', 'RB-6')
,('IB-3', 'RB-7');


