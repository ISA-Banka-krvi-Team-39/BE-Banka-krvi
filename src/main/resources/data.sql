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

insert into Person (name, surname, person_type, uuid, phone_number,person_gender,school,address_id)
values ('Marko', 'Marković',1,9302548671,019348657,0,'adsgdgaf',1);

insert into Person (name, surname, person_type, uuid, phone_number,person_gender,school,address_id)
values ('Milan', 'Milanović',0,1034985672,34091857,1,'adsgdgaf',2);

insert into Person (name, surname, person_type, uuid, phone_number,person_gender,school,address_id)
values ('Ivana', 'Ivanović',2,253498673,03945867,2,'adsgdgaf',3);

insert into Person (name, surname, person_type, uuid, phone_number,person_gender,school,address_id)
values ('Ivan', 'Ivanović',1,253418679,03945867,1,'adsgdgaf',4);

insert into Person (name, surname, person_type, uuid, phone_number,person_gender,school,address_id)
values ('Milana', 'Milanović',1,1032985674,34091857,1,'adsgdgaf',5);

insert into Person (name, surname, person_type, uuid, phone_number,person_gender,school,address_id)
values ('Zorka', 'Marković',0,9302548676,019348657,0,'adsgdgaf',6);

insert into Person (name, surname, person_type, uuid, phone_number,person_gender,school,address_id)
values ('Lenka', 'Milanović',0,1034985677,34091857,1,'adsgdgaf',7);

insert into user_table (email,password,person_id) values ('markovicmarko@gmail.com','sifra123123',1);
insert into user_table (email,password,person_id) values ('milan@gmail.com','sifra123233',2);
insert into user_table (email,password,person_id) values ('ivana@gmail.com','sifra123543',3);
insert into user_table (email,password,person_id) values ('ivan@gmail.com','sifra123123',4);
insert into user_table (email,password,person_id) values ('milana@gmail.com','sifra123233',5);
insert into user_table (email,password,person_id) values ('zorka@gmail.com','sifra123543',6);
insert into user_table (email,password,person_id) values ('lenka@gmail.com','sifra123543',7);

insert into Patient (points,person_id,blood_type)
values (200,2,0);
insert into Patient (points,person_id,blood_type)
values (300,6,1);
insert into Patient (points,person_id,blood_type)
values (400,7,2);


insert into Center (avg_grade, description, name, address_id)
values (2.53,'Osrednji centar','Centar1',1);

insert into Center (avg_grade, description, name, address_id)
values (3.55,'Dobar centar','Centar2',2);

insert into Center (avg_grade, description, name, address_id)
values (4.5,'Izuzetan centar','Centar3',3);

insert into Center (avg_grade, description, name, address_id)
values (2.9,'Malo bolji centar','Centar4',4);

insert into Center (avg_grade, description, name, address_id)
values (3.3,'Malo gori malo bolji centar','Centar5',5);

insert into Center (avg_grade, description, name, address_id)
values (4.2,'Jako dobar centar','Centar6',6);

insert into Center (avg_grade, description, name, address_id)
values (1.3,'Jako los centar','Centar7',7);

insert into Center (avg_grade, description, name, address_id)
values (5.0,'adsgdgaf','Centar',8);

insert into Medical_Staff (person_id,center_id)
values (4,1);

insert into Medical_Staff (person_id,center_id)
values (1,1);

insert into Medical_Staff (person_id,center_id)
values (5,1);

insert into Term (date_time, duration_in_minutes,maximum_space, center_id)
values (now(),20,20,1);

insert into Term (date_time, duration_in_minutes,maximum_space, center_id)
values (now(),20,20,1);

insert into Term (date_time, duration_in_minutes,maximum_space, center_id)
values (now(),20,20,1);

insert into Term (date_time, duration_in_minutes,maximum_space, center_id)
values (now(),20,20,1);

insert into Donating(person_id,term_id)
values (2,2);

insert into Oversees (person_id,term_id)
values (4,1);

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
