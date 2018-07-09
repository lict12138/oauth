-- ###############
--    create MySQL database , if need create, cancel the comment
-- ###############
-- create database if not exists oauth2 default character set utf8;
-- use oauth2 set default character = utf8;

-- ###############
--    grant privileges  to oauth2/oauth2
-- ###############
-- GRANT ALL PRIVILEGES ON oauth2.* TO oauth2@localhost IDENTIFIED BY "oauth2";

-- ###############
-- Domain:  User
-- ###############
Drop table  if exists t_user;
CREATE TABLE t_user (
  c_id int(11) NOT NULL auto_increment,
  c_guid varchar(50) not null unique,
  c_create_time datetime ,
  c_archived tinyint(1) default '0',
  c_email varchar(50),
  c_password varchar(100) not null,
  c_phone varchar(20),
  c_username varchar(20) not null unique,
  c_system_admin tinyint(1) default '0',
  c_enabled tinyint(1) DEFAULT '0',
  c_account_status varchar(10),
  PRIMARY KEY  (c_id)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;


-- ###############
-- Domain:  Privilege
-- ###############
Drop table  if exists t_user_privilege;
CREATE TABLE t_user_privilege (
  c_user_id int(11),
  c_privilege varchar(30),
  KEY c_user_id_index (c_user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ###############
-- Domain:  Application
-- ###############

Drop table  if exists t_application;
CREATE TABLE t_application (
  c_id int(11) NOT NULL auto_increment,
  c_guid varchar(50) not null unique,
  c_create_time datetime ,
  c_archived tinyint(1) default '0',
  c_application_name varchar(50),
  c_creator_uuid varchar(50) not null,
  c_status varchar(20),
  c_client_id varchar(100),
  c_description varchar(100),
  PRIMARY KEY  (c_id)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- #####################
--  Oauth sql  -- MYSQL
-- #####################

Drop table  if exists t_oauth_client_details;
create table t_oauth_client_details (
  c_client_id VARCHAR(255) PRIMARY KEY,
  c_resource_ids VARCHAR(255),
  c_client_secret VARCHAR(255),
  c_scope VARCHAR(255),
  c_authorized_grant_types VARCHAR(255),
  c_web_server_redirect_uri VARCHAR(255),
  c_authorities VARCHAR(255),
  c_access_token_validity INTEGER,
  c_refresh_token_validity INTEGER,
  c_additional_information TEXT,
  c_create_time timestamp default now(),
  c_archived tinyint(1) default '0',
  c_trusted tinyint(1) default '0',
  c_autoapprove VARCHAR (255) default 'false'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


Drop table  if exists t_oauth_access_token;
create table t_oauth_access_token (
  c_create_time timestamp default now(),
  c_token_id VARCHAR(255),
  c_token BLOB,
  c_authentication_id VARCHAR(255),
  c_user_name VARCHAR(255),
  c_client_id VARCHAR(255),
  c_authentication BLOB,
  c_refresh_token VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


Drop table  if exists t_oauth_refresh_token;
create table t_oauth_refresh_token (
  c_create_time timestamp default now(),
  c_token_id VARCHAR(255),
  c_token BLOB,
  c_authentication BLOB
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


Drop table  if exists t_oauth_code;
create table t_oauth_code (
  c_create_time timestamp default now(),
  c_code VARCHAR(255),
  c_authentication BLOB
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- #####################
--  IOT SE sql  -- MYSQL
-- #####################

Drop table if exists t_seInfo;
create table t_seInfo(
  c_id int not null primary key auto_increment,
  c_hid varchar(16) not null,
  c_eccpubkey varchar(128),
  c_rsapubkey text,
  c_downcounter int,
  c_upseed varchar(32),
  c_downseed varchar(32),
  c_macseed varchar(32),
  e_encryptedkey text,
  c_algtype int,
  c_dataTime varchar(32) not null,
  c_master_key_name VARCHAR (32)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

Drop table if exists t_up_counter;
create table t_up_counter(
  c_hid VARCHAR(16) not null,
  c_counter varchar(16) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- #####################
--  AuditLog sql  -- MYSQL
-- #####################
Drop table if exists t_audit_log;
create table t_audit_log(
  c_guid varchar(50) not null unique,
  c_create_time datetime ,
  c_archived tinyint(1) default '0',
  c_creator VARCHAR(20) ,
  c_creator_uuid VARCHAR (50),
  c_ip VARCHAR (20),
  c_content text,
  c_type varchar(20),
  c_application_uuid VARCHAR (50),
  c_hid VARCHAR (16)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Add indexes
create index c_token_id_index on t_oauth_access_token (c_token_id);
create index c_authentication_id_index on t_oauth_access_token (c_authentication_id);
create index c_user_name_index on t_oauth_access_token (c_user_name);
create index c_client_id_index on t_oauth_access_token (c_client_id);
create index c_refresh_token_index on t_oauth_access_token (c_refresh_token);

create index c_token_id_index on t_oauth_refresh_token (c_token_id);

create index c_code_index on t_oauth_code (c_code);

