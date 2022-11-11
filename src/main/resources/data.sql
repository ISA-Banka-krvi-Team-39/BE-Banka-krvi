insert into address (country, city, street_name, street_number) values ('Srbija', 'Beograd', 'Nikole Tesle', 9);
insert into address (country, city, street_name, street_number) values ('Srbija', 'Novi Sad', 'Kraljevica Kristijana', 42);
insert into address (country, city, street_name, street_number) values ('Srbija', 'Novi Sad', ' Kristijana', 34);


insert into Person (name, surname, person_type, uuid, phone_number,person_gender,school,address_id)
values ('Marko', 'Marković',1,930254867,019348657,0,'adsgdgaf',1);

insert into Person (name, surname, person_type, uuid, phone_number,person_gender,school,address_id)
values ('Milan', 'Milanović',0,103498567,34091857,1,'adsgdgaf',2);

insert into Person (name, surname, person_type, uuid, phone_number,person_gender,school,address_id)
values ('Ivana', 'Ivanović',2,25349867,03945867,2,'adsgdgaf',3);

insert into Patient (points,person_id)
values (200,1);
insert into Patient (points,person_id)
values (300,2);
insert into Patient (points,person_id)
values (400,3);


insert into user_table (email,password,person_id) values ('markovicmarko@gmail.com','sifra123123',1);
insert into user_table (email,password,person_id) values ('milan@gmail.com','sifra123233',2);
insert into user_table (email,password,person_id) values ('ivana@gmail.com','sifra123543',3);