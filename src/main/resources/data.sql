insert into address (country, city, street_name, street_number)
values ('Srbija', 'Novi Sad', 'Mikse Dimitrijevica', 12);
insert into address (country, city, street_name, street_number)
values ('Srbija', 'Beograd', 'Nikole Tesle', 9);
insert into address (country, city, street_name, street_number)
values ('Srbija', 'Novi Sad', 'Kraljevica Kristijana', 42);
insert into address (country, city, street_name, street_number)
values ('Srbija', 'Novi Sad', ' Kristijana', 34);
insert into address (country, city, street_name, street_number)
values ('Srbija', 'Novi Sad', 'Mikse Dimitrijevica', 12);
insert into address (country, city, street_name, street_number)
values ('Srbija', 'Beograd', 'Nikole Tesle', 9);
insert into address (country, city, street_name, street_number)
values ('Srbija', 'Novi Sad', 'Kraljevica Kristijana', 42);
insert into address (country, city, street_name, street_number)
values ('Srbija', 'Novi Sad', ' Kristijana', 34);
insert into address (country, city, street_name, street_number)
values ('Srbija', 'Beograd', ' Marka',42);

insert into person_description(antibiotics,infections,kilograms,month_period,pressure,sick,tatoo,tooth)
values(false,false,70,false,false,false,false,false);

insert into Person (name, surname, person_type, uid, phone_number,person_gender,school,address_id,person_description_id)
values ('Marko', 'Marković',1,11111,019348657,0,'adsgdgaf',1,1);

insert into Person (name, surname, person_type, uid, phone_number,person_gender,school,address_id,person_description_id)
values ('Milan', 'Milanović',0,22222,34091857,1,'adsgdgaf',2,1);

insert into Person (name, surname, person_type, uid, phone_number,person_gender,school,address_id,person_description_id)
values ('Ivana', 'Ivanović',2,33333,03945867,2,'adsgdgaf',3,1);

insert into Person (name, surname, person_type, uid, phone_number,person_gender,school,address_id,person_description_id)
values ('Ivan', 'Ivanović',1,44444,03945867,1,'adsgdgaf',4,1);

insert into Person (name, surname, person_type, uid, phone_number,person_gender,school,address_id,person_description_id)
values ('Milana', 'Milanović',1,55555,34091857,1,'adsgdgaf',5,1);

insert into Person (name, surname, person_type, uid, phone_number,person_gender,school,address_id,person_description_id)
values ('Zorka', 'Marković',1,66666,019348657,0,'adsgdgaf',6,1);

insert into Person (name, surname, person_type, uid, phone_number,person_gender,school,address_id,person_description_id)
values ('Lenka', 'Milanović',0,77777,34091857,1,'adsgdgaf',7,1);

insert into user_table (email,password,person_id,enabled,last_password_reset_date,activation_code) values ('markovicmarko@gmail.com','$2a$10$weZMibSttAmFfa1aYJ9F.OxWNTH2hwNP8D/E5q4jSLnRcjZ3pg/QW',1,true,'2017-10-01 21:58:58.508-07','agfohiudagfd');
insert into user_table (email,password,person_id,enabled,last_password_reset_date,activation_code) values ('milan@gmail.com','$2a$10$weZMibSttAmFfa1aYJ9F.OxWNTH2hwNP8D/E5q4jSLnRcjZ3pg/QW',2,true,'2017-10-01 21:58:58.508-07','zcvbkljnzvcbjknl');
insert into user_table (email,password,person_id,enabled,last_password_reset_date,activation_code) values ('ivana@gmail.com','$2a$10$weZMibSttAmFfa1aYJ9F.OxWNTH2hwNP8D/E5q4jSLnRcjZ3pg/QW',3,true,'2017-10-01 21:58:58.508-07','aaaaaaaagrhukgurha');
insert into user_table (email,password,person_id,enabled,last_password_reset_date,activation_code) values ('ivan@gmail.com','$2a$10$weZMibSttAmFfa1aYJ9F.OxWNTH2hwNP8D/E5q4jSLnRcjZ3pg/QW',4,true,'2017-10-01 21:58:58.508-07','m0qv75m0q75vtm0q');
insert into user_table (email,password,person_id,enabled,last_password_reset_date,activation_code) values ('milana@gmail.com','$2a$10$weZMibSttAmFfa1aYJ9F.OxWNTH2hwNP8D/E5q4jSLnRcjZ3pg/QW',5,true,'2017-10-01 21:58:58.508-07','a079gvra5gv70ja05jgv7k');
insert into user_table (email,password,person_id,enabled,last_password_reset_date,activation_code) values ('zorka@gmail.com','$2a$10$weZMibSttAmFfa1aYJ9F.OxWNTH2hwNP8D/E5q4jSLnRcjZ3pg/QW',6,true,'2017-10-01 21:58:58.508-07','07j447j0vq0qv7j4f07j');
insert into user_table (email,password,person_id,enabled,last_password_reset_date,activation_code) values ('lenka@gmail.com','$2a$10$weZMibSttAmFfa1aYJ9F.OxWNTH2hwNP8D/E5q4jSLnRcjZ3pg/QW',7,true,'2017-10-01 21:58:58.508-07','615glzxk16z5g9l1569l');

insert into Patient (points,person_id,blood_type,penal)
values (200,2,0,3);
insert into Patient (points,person_id,blood_type,penal)
values (300,6,1,1);
insert into Patient (points,person_id,blood_type,penal)
values (400,7,2,2);

insert into system_admin(person_id,was_logged_in)
values(1,true);

insert into Center (avg_grade, description, name, address_id,start_working_hours,end_working_hours)
values (2.53,'Osrednji centar','Centar1',1,'09:00:00','20:00:00');

insert into Center (avg_grade, description, name, address_id,start_working_hours,end_working_hours)
values (3.55,'Dobar centar','Centar2',2,'09:00:00','20:00:00');

insert into Center (avg_grade, description, name, address_id,start_working_hours,end_working_hours)
values (4.5,'Izuzetan centar','Centar3',3,'09:00:00','20:00:00');

insert into Center (avg_grade, description, name, address_id,start_working_hours,end_working_hours)
values (2.9,'Malo bolji centar','Centar4',4,'09:00:00','20:00:00');

insert into Center (avg_grade, description, name, address_id,start_working_hours,end_working_hours)
values (3.3,'Malo gori malo bolji centar','Centar5',5,'09:00:00','20:00:00');

insert into Center (avg_grade, description, name, address_id,start_working_hours,end_working_hours)
values (4.2,'Jako dobar centar','Centar6',6,'09:00:00','20:00:00');

insert into Center (avg_grade, description, name, address_id,start_working_hours,end_working_hours)
values (1.3,'Jako los centar','Centar7',7,'09:00:00','20:00:00');

insert into Center (avg_grade, description, name, address_id,start_working_hours,end_working_hours)
values (5.0,'adsgdgaf','Centar',8,'09:00:00','20:00:00');

insert into Medical_Staff(person_id,center_id)
values (4,1);

insert into Medical_Staff(person_id,center_id)
values (1,1);

insert into Medical_Staff(person_id,center_id)
values (5,1);

insert into Term (date_time, duration_in_minutes, maximum_space, center_id,person_id, state, medical_staff,version)
values ('2023-01-01 21:00:00',20,1,1,2,1,1,0);

insert into Term (date_time, duration_in_minutes, maximum_space, center_id,person_id, state, medical_staff,version)
values ('2023-07-01 21:00:00',20,1,1,7,1,1,0);

insert into Term (date_time, duration_in_minutes, maximum_space, center_id,person_id, state, medical_staff,version)
values ('2022-01-01 21:00:00',20,1,1,6,1,1,0);

insert into Term (date_time, duration_in_minutes, maximum_space, center_id,person_id, state, medical_staff,version)
values ('2022-1-23 13:00:00',20,1,1,7,2,1,0);

insert into Term (date_time, duration_in_minutes, maximum_space, center_id, state, medical_staff, person_id,version)
values ('2022-6-23 13:00:00',30,1,1,2,1,7,0);
insert into Term (date_time, duration_in_minutes, maximum_space, center_id, state, medical_staff, person_id,version)
values ('2022-2-20 15:00:00',45,1,1,2,1,7,0);
insert into Term (date_time, duration_in_minutes, maximum_space, center_id, state, medical_staff,version)
values ('2023-2-23 13:00:00',40,1,1,0,1,0);
insert into Term (date_time, duration_in_minutes, maximum_space, center_id, state, medical_staff,version)
values ('2023-5-1 13:00:00',20,1,1,0,1,0);
insert into Term (date_time, duration_in_minutes, maximum_space, center_id, state, medical_staff,version)
values ('2023-5-15 9:00:00',35,1,1,0,1,0);
insert into Term (date_time, duration_in_minutes, maximum_space, center_id, state, medical_staff, person_id,version)
values ('2023-8-20 15:00:00',35,1,1,1,1,7,0);
insert into Term (date_time, duration_in_minutes, maximum_space, center_id, state, medical_staff,version)
values ('2023-8-20 16:00:00',30,1,3,0,1,0);
insert into Term (date_time, duration_in_minutes, maximum_space, center_id, state, medical_staff,version)
values ('2023-5-20 15:09:00',60,1,5,0,1,0);

insert into Question (question) 
values ('Have you ever donated blood or blood components voluntarily?');
insert into Question (question)
values ('Have you ever been rejected as a blood  donor?');
insert into Question (question)
values ('Do you currently feel healthy?');
insert into Question (question)
values ('Did you eat anything before coming to donate blood?');
insert into Question (question)
values ('Have you taken any medicines in the last 2-3 days');
insert into Question (question)
values ('Have you suddenly lost weight in the last 6 months?');
insert into Question (question)
values ('Have you drunk alcohol in the last 6 hours?');
insert into Question (question)
values ('Have you had a tooth extracted in the past 7 days?');
insert into Question (question)
values ('Do you have a dangerous occupation or hobby?');
insert into Question (question)
values ('Do you take any medications regularly (daily)?');
insert into Question (question)
values ('Do you have any changes in your skin or suffer from allergies?');
insert into Question (question)
values ('Do you bleed for a long time after an injury?');

insert into blood_bag (amount,blood_type,center_id)
values(5,0,1);
insert into blood_bag (amount,blood_type,center_id)
values(5,1,1);
insert into blood_bag (amount,blood_type,center_id)
values(5,2,1);

insert into equipment (amount,subject)
values(5,'Needle');
insert into equipment (amount,subject)
values(5,'Bag');

insert into role (name)
values('ROLE_ADMIN');
insert into role (name)
values('ROLE_USER');
insert into role (name)
values('ROLE_MANAGER');
insert into role (name)
values('ROLE_NURSE');

insert into user_role(user_id,role_id)
values(1,1);
insert into user_role(user_id,role_id)
values(2,2);
insert into user_role(user_id,role_id)
values(3,3);
insert into user_role(user_id,role_id)
values(7,2);
insert into user_role(user_id,role_id)
values(6,2);
insert into user_role(user_id,role_id)
values(5,4);


