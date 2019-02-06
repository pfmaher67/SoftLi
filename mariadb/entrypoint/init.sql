create database SoftLi;


create table SoftLi.LicenseRight(
 id varchar(129),
 appId varchar(64),
 licenseModelId varchar(64),
 qtyOwned int,
 qtyReserved int,
 PRIMARY KEY(id)
 );

insert into SoftLi.LicenseRight(id, appId, licenseModelId, qtyOwned, qtyReserved) values
 ('App-1-Model-1', 'App-1', 'Model-1', 32, 0)
 ,('App-2-Model-1', 'App-2', 'Model-1', 16, 0)
 ,('App-2-Model-5', 'App-2', 'Model-5', 64, 0)
 ,('App-3-Model-3', 'App-3', 'Model-3', 3, 0);



create table SoftLi.LicenseModel(
 id varchar(64),
 name varchar(64),
 licenseMetricId int,
 softwareCategoryId int,
 PRIMARY KEY(id)
 );
 
insert into SoftLi.LicenseModel(id, name, licenseMetricId, softwareCategoryId) values
('Model-1', 'WAS ND', 0, 0)
,('Model-2', 'RHEL', 2, 1)
,('Model-3', 'MongoDB', 2, 0)
,('Model-4', 'Qualys', 2, 1)
,('Model-5', 'Kafka', 0, 0);



create table SoftLi.SoftwareRelease(
 id varchar(64),
 name varchar(64),
 version varchar(64),
 licenseModelId varchar(64),
 PRIMARY KEY(id),
 FOREIGN KEY(licenseModelId) REFERENCES SoftLi.LicenseModel(id)
 );
 
insert into SoftLi.SoftwareRelease(id, name, version, licenseModelId) values
('SWRel-1', 'WAS ND exe', '8.1.1.13', 'Model-1')
,('SWRel-2', 'WAS ND exe2', '8.1.1.14', 'Model-1')
,('SWRel-3', 'RHEL 6', '6', 'Model-2')
,('SWRel-4', 'RHEL 7', '7', 'Model-2')
,('SWRel-5', 'Mongo', '3.6', 'Model-3')
,('SWRel-6', 'Qualys', 'X', 'Model-4')
,('SWRel-7', 'Kafka', 'Y', 'Model-5');
 


create table SoftLi.Image(
 id varchar(64),
 platform varchar(64),
 PRIMARY KEY(id)
 );
 
insert into SoftLi.Image(id, platform) values
('Image-1', 'AWS')
,('Image-2', 'AWS')
,('Image-3', 'AWS');



create table SoftLi.manifest(
 imageId varchar(64),
 swReleaseId varchar(64),
 PRIMARY KEY(imageId, swReleaseId),
 FOREIGN KEY(imageId) REFERENCES SoftLi.Image(id),
 FOREIGN KEY(swReleaseId) REFERENCES SoftLi.SoftwareRelease(id)
 );
 
insert into SoftLi.manifest(imageId, swReleaseId) values
('Image-1', 'SWRel-1')
,('Image-1', 'SWRel-3')
,('Image-1', 'SWRel-6')
,('Image-2', 'SWRel-4')
,('Image-2', 'SWRel-6')
,('Image-2', 'SWRel-5')
,('Image-3', 'SWRel-2')
,('Image-3', 'SWRel-4')
,('Image-3', 'SWRel-6')
,('Image-3', 'SWRel-7');



create user 'dbu-softli' identified by 'softli-pw';
grant all privileges on `SoftLi`.* to 'dbu-softli'@'%';




