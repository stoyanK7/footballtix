/*
-- Query: SELECT * FROM footballtix.user
LIMIT 0, 50000

-- Date: 2021-12-20 14:02
*/
INSERT INTO `footballtix.user` (`id`,`email`,`enabled`,`full_name`,`locked`,`password`,`role`) VALUES (1,'admin@gmail.com','1','Administrator','0','$2a$10$opabYehWyw/tp1Rsty9wU.YOzqRaLda9UoWgW7z4svRLxnjwUTEw6','ADMIN');
INSERT INTO 
footballtix.user 
(`id`,`email`,`enabled`,`full_name`,`locked`,`password`,`role`) 
VALUES 
(200,'test@gmail.com',b'1','Test account',b'0','$2a$10$soAC.AtT7UxawEMdyL1Za.e2sNDrzC5ECbSJRaPL9ZHWErKIOy1KO','ADMIN');

-- Query: SELECT * FROM footballtix.orders
LIMIT 0, 50000

-- Date: 2021-12-20 14:19
*/
INSERT INTO `footballtix.orders` (`id`,`account_email`,`address`,`city`,`country`,`email`,`full_name`,`mobile_phone`,`postcode`,`transaction_date_time`,`transaction_id`,`football_match_id`) VALUES (3,'test@gmail.com','Johannes van der Waalsweg 72','Eindhoven','Netherlands','test@gmail.com','Test','+3228788908621','5633JA','2021-12-20 14:18:43.000000','8LR17813735799644',1);