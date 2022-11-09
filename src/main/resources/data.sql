
insert into address (country, city, street_name, street_number) values ('Srbija', 'Novi Sad', 'Mikse Dimitrijevica', 12);
insert into address (country, city, street_name, street_number) values ('Srbija', 'Beograd', 'Nikole Tesle', 9);
insert into address (country, city, street_name, street_number) values ('Srbija', 'Novi Sad', 'Kraljevica Kristijana', 42);
insert into center (center_id,avg_grade,description,name,address_id) values(1,2.57,'bla bla','centar',1);
insert into term (term_id,date,duration,time) values(1,TO_DATE('17/12/2015', 'DD/MM/YYYY'),30,now());
insert into terms (terms_id,center_id,term_id) values(1,1,1);
insert into person (person_id,name,phone_number,school,surname,uuid,address_id,center_id) values(1,'Jovan',123,'Srednja','Jovic',1,1,1);
insert into user_table (user_id,email,password,person_type,person_id) values (1,'blabla','blabla',1,1);
insert into working (person_id,terms_id) values(1,1);