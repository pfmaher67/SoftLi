
insert into LicenseModel(id, name, licenseMetricId, softwareCategoryId) values
('MD-1', 'WAS ND', 0, 0)
,('MD-2', 'RHEL', 2, 1)
,('MD-3', 'MongoDB', 2, 0)
,('MD-4', 'Qualys', 2, 1)
,('MD-5', 'Kafka', 0, 0);

insert into LicenseRight(id, appId, licenseModelId, qtyOwned, qtyReserved) values
 ('AD-1-MD-1', 'AD-1', 'MD-1', 32, 0)
 ,('AD-2-MD-1', 'AD-2', 'MD-1', 16, 0)
 ,('AD-2-MD-5', 'AD-2', 'MD-5', 64, 0)
 ,('AD-3-MD-3', 'AD-3', 'MD-3', 3, 0);
 
insert into SoftwareRelease(id, name, version, licenseModelId) values
('RD-1', 'WAS ND exe', '8.1.1.13', 'MD-1')
,('RD-2', 'WAS ND exe2', '8.1.1.14', 'MD-1')
,('RD-3', 'RHEL 6', '6', 'MD-2')
,('RD-4', 'RHEL 7', '7', 'MD-2')
,('RD-5', 'Mongo', '3.6', 'MD-3')
,('RD-6', 'Qualys', 'X', 'MD-4')
,('RD-7', 'Kafka', 'Y', 'MD-5');

insert into Image(id, platform) values
('ID-1', 'AWS')
,('ID-2', 'AWS')
,('ID-3', 'AWS')
,('ID-4', 'AWS');

insert into EC2InstanceType(instanceType, vcpu, memory) values
('t2.nano', 1, 0.5)
,('t2.micro', 1, 1)
,('t2.small', 1, 2)
,('t2.medium', 1, 2)
,('t2.large', 1, 8)
,('t2.xlarge', 4, 16)
,('t2.2xlarge', 8, 32)
,('t3.nano', 2, 0.5)
,('t3.micro', 2, 1)
,('t3.small', 2, 2)
,('t3.medium', 2, 2)
,('t3.large', 2, 8)
,('t3.xlarge', 4, 16)
,('t3.2xlarge', 8, 32)
,('m5.large', 2, 8)
,('m5.xlarge', 4, 16)
,('m5.2xlarge', 8, 32)
,('m5.4xlarge', 16, 64)
,('m5.12xlarge', 48, 192)
,('m5.24xlarge', 96, 384);

insert into manifest(imageId, swReleaseId) values
('ID-1', 'RD-1')
,('ID-1', 'RD-3')
,('ID-1', 'RD-6')
,('ID-2', 'RD-4')
,('ID-2', 'RD-6')
,('ID-2', 'RD-5')
,('ID-3', 'RD-2')
,('ID-3', 'RD-4')
,('ID-3', 'RD-6')
,('ID-3', 'RD-7');
