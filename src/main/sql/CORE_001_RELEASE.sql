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
created_by INTEGER REFERENCES system.user_account(user_id),
updated_by INTEGER REFERENCES system.user_account(user_id),
created_at timestamp without time zone NOT NULL,
update_at timestamp without time zone NOT NULL);

-- 14/12/2022 12:50 PM SHEENA AP
-- Alter table user_account to add foreign key constrain to status
ALTER TABLE system.user_account ADD FOREIGN KEY(status) REFERENCES public.status_code(status_code);

-- 14/12/2022 12:50 PM SHEENA AP
-- Alter table user_account to add foreign key constrain to company_id
ALTER TABLE system.user_account ADD FOREIGN KEY(company_id) REFERENCES org.company(company_id);

-- 28/12/2022 03:15 PM SHEENA AP
-- Alter table user_account to add column email
ALTER TABLE system.user_account ADD COLUMN email TEXT NOT NULL;

INSERT INTO public.status_code (status_code,status_name,status_code_context,notes) values
(110,'Active User','User account',' '),
(111,'Inactive User','User account',' ');

INSERT INTO org.company(company_code,company_name,status,created_at,update_at) VALUES('INV','Innovature Software Labs',110,'2020-11-01 17:03:06','2020-11-01 17:03:06');

INSERT INTO system.user_account (user_name,password,email,status,company_id,created_by,updated_by,created_at,update_at) VALUES('Sheena','123456','sheena.ap@innovaturelabs.com',100,1,1,1,'2020-11-01 17:03:06','2020-11-01 17:03:06');

UPDATE org.company SET created_by=1,updated_by=1;

ALTER TABLE  org.company ALTER COLUMN created_by SET NOT NULL;
ALTER TABLE  org.company ALTER COLUMN updated_by SET NOT NULL;

CREATE TABLE hr.staff(
staff_id SERIAL NOT NULL PRIMARY KEY,
staff_name TEXT NOT NULL,
employee_code TEXT NOT NULL,
status INTEGER NOT NULL REFERENCES public.status_code(status_code),
company_id INTEGER NOT NULL REFERENCES org.company(company_id),
created_at timestamp without time zone NOT NULL,
update_at timestamp without time zone NOT NULL);

ALTER TABLE hr.staff ADD COLUMN created_by INTEGER REFERENCES hr.staff(staff_id);
ALTER TABLE hr.staff ADD COLUMN updated_by INTEGER REFERENCES hr.staff(staff_id);
ALTER TABLE hr.staff ADD COLUMN email TEXT NOT NULL UNIQUE;
ALTER TABLE hr.staff ADD COLUMN address TEXT;
ALTER TABLE hr.staff ADD COLUMN date_of_birth TEXT;
ALTER TABLE hr.staff ADD COLUMN department INTEGER NOT NULL;
ALTER TABLE hr.staff ADD COLUMN phone_number TEXT NOT NULL;







