/* BASE TRANSACTION TABLES */
CREATE TABLE IF NOT EXISTS tenant (rootid bigint NOT NULL AUTO_INCREMENT PRIMARY KEY, tenantid varchar(50) NOT NULL UNIQUE, tenantname varchar(100) NOT NULL, active BOOL DEFAULT TRUE, purgetenant BOOL DEFAULT FALSE, timecreated bigint, timeupdated bigint);
CREATE TABLE IF NOT EXISTS tenantdetails (rootid bigint NOT NULL AUTO_INCREMENT PRIMARY KEY, tenantrootid bigint NOT NULL UNIQUE, tenantcontact varchar(15), tenantemail varchar(100), tenantbusinessemail varchar(100), businessemailpassword varchar(100), tenantstreet varchar(200), tenantcity varchar(100), tenantpin varchar(10), tenantlogo MEDIUMBLOB, tenantfacebook varchar(100), tenanttwitter varchar(100), tenantinsta varchar(100), gstin varchar(50), fssai varchar(50), tagline varchar(100), gmaplocation varchar(1000), timecreated bigint, timeupdated bigint, CONSTRAINT FOREIGN KEY(tenantrootid) REFERENCES tenant(rootid));
CREATE TABLE IF NOT EXISTS tenantorigins (rootid bigint NOT NULL AUTO_INCREMENT PRIMARY KEY, tenantrootid bigint NOT NULL, origin varchar(300), timecreated bigint, timeupdated bigint, CONSTRAINT FOREIGN KEY(tenantrootid) REFERENCES tenant(rootid));

CREATE TABLE IF NOT EXISTS featuretoggle (rootid bigint NOT NULL AUTO_INCREMENT PRIMARY KEY, tenantrootid bigint NOT NULL, featurename varchar(30) NOT NULL UNIQUE, active BOOL DEFAULT FALSE, timecreated bigint, timeupdated bigint, CONSTRAINT FOREIGN KEY(tenantrootid) REFERENCES tenant(rootid));
CREATE TABLE IF NOT EXISTS scheduledtaskaudit (rootid bigint NOT NULL AUTO_INCREMENT PRIMARY KEY, tenantrootid bigint NOT NULL, taskname varchar(75) NOT NULL, starttime DATETIME DEFAULT CURRENT_TIMESTAMP, endtime DATETIME , status varchar(25), failureinfo varchar(1000), timecreated bigint, timeupdated bigint, CONSTRAINT FOREIGN KEY(tenantrootid) REFERENCES tenant(rootid));


/* DEV */
/* initial data load */
insert into tenant (rootid, tenantid, tenantname, active, purgetenant, timecreated, timeupdated) values (1, 'devTenant', 'devRealm', true, false, 0, 0);
insert into tenantdetails (rootid, tenantrootid, tenantcontact, tenantemail, tenantbusinessemail, businessemailpassword, tenantstreet, tenantcity, tenantpin, tenantfacebook, tenanttwitter, tenantinsta, timecreated, timeupdated) values (1, 1, '1234567890', 'nuttyShop@gmail.com', 'do.not.reply.application.ordering@gmail.com', 'devpassword@123', '13, B block, Nelson Road', 'Chennai', '600028', 'https://www.facebook.com/', 'https://twitter.com/', 'https://www.instagram.com/', 0, 0);
insert into tenantorigins (rootid, tenantrootid, origin, timecreated, timeupdated) values (1, 1, 'http://localhost:4200', 0, 0);
insert into tenantorigins (rootid, tenantrootid, origin, timecreated, timeupdated) values (2, 1, '*', 0, 0);