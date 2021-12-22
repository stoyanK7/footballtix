USE footballtix;
SET FOREIGN_KEY_CHECKS=0;
REPLACE INTO footballtix.user (`id`,`email`,`enabled`,`full_name`,`locked`,`password`,`role`) VALUES (1000,'admin@gmail.com',b'1','Administrator',b'0','$2a$10$opabYehWyw/tp1Rsty9wU.YOzqRaLda9UoWgW7z4svRLxnjwUTEw6','ADMIN');
REPLACE INTO footballtix.user (`id`,`email`,`enabled`,`full_name`,`locked`,`password`,`role`) VALUES (1001,'test@gmail.com',b'1','Test account',b'0','$2a$10$soAC.AtT7UxawEMdyL1Za.e2sNDrzC5ECbSJRaPL9ZHWErKIOy1KO','ADMIN');
REPLACE INTO footballtix.orders (`id`,`account_email`,`address`,`city`,`country`,`email`,`full_name`,`mobile_phone`,`postcode`,`transaction_date_time`,`transaction_id`,`football_match_id`) VALUES (1008,'test@gmail.com','Johannes van der Waalsweg 72','Eindhoven','Netherlands','test@gmail.com','Test','+3228788908621','5633JA','2021-12-20 14:18:43.000000','8LR17813735799644',22);
REPLACE INTO footballtix.football_match (`id`,`away_team`,`home_team`,`league`,`location`,`price_per_ticket`,`stadium`,`starting_date_time`,`tickets_available`) VALUES (22,'Cypress test away','Cypress test home','Cypress test league','Cypress test location',100,'Cypress test staidum','2223-12-12 00:00:00.000000',1500);