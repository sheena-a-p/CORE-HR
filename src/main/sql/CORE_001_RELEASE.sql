--CORE_001_RELEASE.sql 08/11/2022 12:30 PM

-- 14/12/2022 12:00 PM SHEENA AP
-- Create database core with owner postgres
CREATE DATABASE core  WITH OWNER = postgres ENCODING = 'UTF8' CONNECTION LIMIT = -1;

-- 14/12/2022 12:00 PM SHEENA AP
-- Create schema system
CREATE SCHEMA system;

-- 14/12/2022 12:00 PM SHEENA AP
-- Create schema hr
CREATE SCHEMA hr;

-- 14/12/2022 12:15 PM SHEENA AP
-- Create table user
CREATE TABLE system.user_account (
user_id SERIAL NOT NULL PRIMARY KEY,
user_name TEXT NOT NULL,
password TEXT NOT NULL,
password_token TEXT,
verification_token TEXT,
company_id INTEGER NOT NULL,
status INTEGER NOT NULL,
created_by INTEGER NOT NULL,
updated_by INTEGER NOT NULL,
created_at timestamp without time zone NOT NULL,
update_at timestamp without time zone NOT NULL);

-- 14/12/2022 12:20 PM SHEENA AP
-- Create table status codes
CREATE TABLE public.status_code (
status_code INTEGER NOT NULL PRIMARY KEY,
status_name TEXT NOT NULL,
status_code_context TEXT NOT NULL,
notes TEXT);

-- 14/12/2022 12:30 PM SHEENA AP
-- Inserting status codes for active and inactive user accounts
INSERT INTO public.status_code (status_code,status_name,status_code_context,notes) values
(100,'Active User','User account',' '),
(101,'Inactive User','User account',' ');

-- 14/12/2022 12:00 PM SHEENA AP
-- Create schema org
CREATE schema org;

-- 14/12/2022 12:00 PM SHEENA AP
-- Create table company
CREATE TABLE org.company(
company_id SERIAL NOT NULL PRIMARY KEY,
company_code TEXT NOT NULL UNIQUE,
company_name TEXT NOT NULL,
status INTEGER NOT NULL REFERENCES public.status_code(status_code),
created_by INTEGER NOT NULL REFERENCES system.user_account(user_id),
updated_by INTEGER NOT NULL REFERENCES system.user_account(user_id),
created_at timestamp without time zone NOT NULL,
update_at timestamp without time zone NOT NULL);

-- 14/12/2022 12:50 PM SHEENA AP
-- Alter table user_account to add foreign key constrain to status
ALTER TABLE system.user_account ADD FOREIGN KEY(status) REFERENCES public.status_code(status_code);

-- 14/12/2022 12:50 PM SHEENA AP
-- Alter table user_account to add foreign key constrain to company_id
ALTER TABLE system.user_account ADD FOREIGN KEY(company_id) REFERENCES org.company(company_id);







