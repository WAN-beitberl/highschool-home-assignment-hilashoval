use hila;
create table highschoolInfo(id int NOT NULL AUTO_INCREMENT,
                            first_name text NOT NULL,
                            last_name text NOT NULL,
                            email text,
                            gender text,
                            ip_addres text, cm_height int, age int CHECK(age>0),
                            has_car text, car_color text,
                            grade int CHECK(grade between 0 and 100), grade_avg double CHECK(grade_avg between 0 and 100),
                            identification_card int, PRIMARY KEY (id),
                            CONSTRAINT chk_nulls CHECK ( (has_car IS NULL AND car_color IS NULL) OR (has_car IS NOT NULL AND car_color IS NOT NULL) )) create table highschool_friendships(id int NOT NULL AUTO_INCREMENT,
friend_id int NOT NULL, other_firend_id int NOT NULL,PRIMARY KEY (id));
create view ids_and_avgs
as select identification_card, grade_avg from highschool;