INSERT INTO `profile` (`Id`, `email`, `password`, `address1`, `zip`, `phone`, `location`, `facility`, `relationship`, `gender`, `goal`, `first_name`, `last_name`, `profile_picture`, `dob`, `group_id`, `parent_id`, `address2`, `has_plan`, `cell`, `city`, `state`)
VALUES
	(1, 'JaneAusten@lighthouse.com', 'a', '1444 S. Alameda St.', '90021', '(213) 765-3100', '', '', '', 'Female', '', 'Jane', 'Austen', 'https://dl.dropboxusercontent.com/s/nou3oes1k6snx8a/u342.png', '1/25/1882', 1, 0, '', 0, '(213) 876-5555', 'Los Angeles', 'CA'),
	(4, '', '', '1444 S. Alameda St.', '90021', '', 'In Placement', 'GUARDIANS OF LOVE', 'Son', 'Male', 'Reunification', 'Walter', 'Austen', 'https://dl.dropboxusercontent.com/s/6fcdjqaufim9njo/u298.png', '1/25/1909', 1, 1, '', 1, '', 'Los Angeles', 'CA'),
	(5, '', '', '1444 S. Alameda St.', '90021', '', 'In Placement', 'EGGLESTON FAMILY SERVICES', 'Daughter', 'Female', 'Reunification', 'Lilly', 'Austen', 'https://dl.dropboxusercontent.com/s/9f3kny5kq0saalh/u322.png', '3/23/1913', 1, 1, '', 1, '', 'Los Angeles', 'CA'),
	(10, 'atrason@foster.ca.gov', 'password', '110 Lewis St Eatontown NJ', NULL, NULL, NULL, NULL, 'Caseworker', 'Female', NULL, 'Ann', 'Trason', 'https://dl.dropboxusercontent.com/s/tms282gf9aua6wy/ann-trason.png', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL),
	(11, 'cacaremail@foster.ca.gov', 'PASSWORD', '110 Lewis St Eatontown NJ', NULL, NULL, NULL, NULL, 'Caseworker', NULL, NULL, 'System', 'User', NULL, NULL, NULL, 0, NULL, 0, NULL, NULL, NULL);

INSERT INTO profile (id, email) VALUES
(14, 'ashley@email.com'),
(15, 'mike@email.com'),
(16, 'tim@email.com');

INSERT INTO `profile` (`Id`, `email`, `password`, `address1`, `zip`, `phone`, `location`, `facility`, `relationship`, `gender`, `goal`, `first_name`, `last_name`, `profile_picture`, `dob`, `group_id`, `parent_id`, `address2`, `has_plan`, `cell`, `city`, `state`)
VALUES
	(2, 'maria@emailaddress.com', '', '1120 N Street', '94287', '555.666.7777', '', '', '', 'Female', '', 'Maria', 'Jiminez', 
	'https://dl.dropboxusercontent.com/s/38lwpw2ytek0a8r/Maria-Jimenez.png', '9/30/1980', 1, 0, '', 0, '', 'Sacramento', 'CA'),
	
	(3, 'lewissmith@emailaddress.com', '', '1657 Riverside Drive', '96001', '555.444.3333', '', '', '', 'Male', '', 'Lewis', 'Smith', 
	'https://dl.dropboxusercontent.com/s/70fj6nvpbr5obuk/Lewis-Smith.png', '11/9/1972', 1, 0, '', 0, '', 'Reading', 'CA'),
	
	(6, '', '', '1120 N Street', '94287', '', 'IN Placement', 'GUARDIANS OF LOVE', 'Son', 'Male', 'Reunification', 'Jose', 
	'Jiminez', 'https://dl.dropboxusercontent.com/s/c84cckyhl3b10gq/jose%20jimenez.png', '7/13/2001', 1, 1, '', 1, '', 'Sacramento', 'CA'),
	(7, '', '', '1120 N Street', '94287', '', 'IN Placement', 'EGGLESTON FAMILY SERVICES', 'Daughter', 'Female', 'Reunification', 
	'Josefina', 'Jiminez', 'https://dl.dropboxusercontent.com/s/j4sb2bnrpktqlh0/josefina-jimenez.png', '3/23/2007', 1, 1, '', 1, '', 'Sacramento', 'CA'),
	
	(8, '', '', '1444 S. Alameda St.', '96001', '', 'IN Placement', 'GUARDIANS OF LOVE', 'Son', 'Male', 'Reunification', 'James', 
	'Smith', 'https://dl.dropboxusercontent.com/s/4lef88g8zw19u7g/james%20smith.png', '11/25/1909', 1, 1, '', 1, '', 'Redding', 'CA');

INSERT INTO `message` (`id`, `to_id`, `from_id`, `body`, `subject`, `is_read`, `created_at`)
VALUES
	(31, 1, 11, 'Welcome TO the State of California CA CareMail system. Here you can manage everything about your children AND their CASE plan.  You have already started the setup BY completing your USER profile. NEXT steps are TO CREATE the profiles of ANY other family AND house hold members. You can DO this BY clicking ON the “My Profile” link FROM the Home page.', 'Welcome TO CA CareMail', 0, '2016-06-08 13:59:22'),
	(32, 1, 10, '​​A NEW CASE plan has been created FOR Walter Austen. A CASE plan will HELP you manage Walter’s progress AND ANY services OR NEXT steps. You can ADD your comments AND progress IN this section. Your CASE worker can DO the same. However, your CASE worker IS the only ONE who will be able TO UPDATE OR CHANGE the settings FOR the service. The goal FOR Walter’s Plan IS Reunification.', 'NEW Plan Created FOR Walter', 0, '2016-06-08 14:21:22'),
	(33, 1, 10, 'A NEW CASE plan has been created FOR Walter Austen. A CASE plan will HELP you manage Lilly’s progress AND ANY services OR NEXT steps. You can ADD your comments AND progress IN this section. Your CASE worker can DO the same. However, your CASE worker IS the only ONE who will be able TO UPDATE OR CHANGE the settings FOR the service. The goal FOR Lilly’s Plan IS Reunification.', 'NEW Plan Created FOR Lilly', 0, '2016-06-08 15:08:22'),
	(34, 2, 11, '​​Welcome TO the State of California CA CareMail system. Here you can manage everything about your children AND their CASE plan.  You have already started the setup BY completing your USER profile. NEXT steps are TO CREATE the profiles of ANY other family AND house hold members. You can DO this BY clicking ON the “My Profile” link FROM the Home page.', '​Welcome TO CA CareMail', 0, '2016-06-08 13:59:22'),
	(35, 2, 10, '​​A NEW CASE plan has been created FOR Josefina Jiminez. A CASE plan will HELP you manage Walter’s progress AND ANY services OR NEXT steps. You can ADD your comments AND progress IN this section. Your CASE worker can DO the same. However, your CASE worker IS the only ONE who will be able TO UPDATE OR CHANGE the settings FOR the service. The goal FOR Walter’s Plan IS Reunification.', 'NEW Plan Created FOR Josefina', 0, '2016-06-08 14:21:22'),
	(36, 2, 10, 'A NEW CASE plan has been created FOR Jose Jiminez. A CASE plan will HELP you manage Lilly’s progress AND ANY services OR NEXT steps. You can ADD your comments AND progress IN this section. Your CASE worker can DO the same. However, your CASE worker IS the only ONE who will be able TO UPDATE OR CHANGE the settings FOR the service. The goal FOR Lilly’s Plan IS Reunification.', 'NEW Plan Created FOR Jose', 0, '2016-06-08 15:08:22'),
	(37, 3, 11, '​​Welcome TO the State of California CA CareMail system. Here you can manage everything about your children AND their CASE plan.  You have already started the setup BY completing your USER profile. NEXT steps are TO CREATE the profiles of ANY other family AND house hold members. You can DO this BY clicking ON the “My Profile” link FROM the Home page.', '​Welcome TO CA CareMail', 0, '2016-06-08 13:59:22'),
	(38, 3, 10, '​​A NEW CASE plan has been created FOR James Smith. A CASE plan will HELP you manage Walter’s progress AND ANY services OR NEXT steps. You can ADD your comments AND progress IN this section. Your CASE worker can DO the same. However, your CASE worker IS the only ONE who will be able TO UPDATE OR CHANGE the settings FOR the service. The goal FOR Walter’s Plan IS Reunification.', 'NEW Plan Created FOR James', 0, '2016-06-08 14:21:22');
