insert into person_type (type) values
    ('Student'),
    ('Teacher');

insert into person (id,name, age, type_id) values
        (1,'Jessica Stones', 25, 1),       -- Assuming 'Student' has typeId = 1 in the person_type table
        (2,'Jane Smith', 30, 2),     -- Assuming 'Teacher' has typeId = 2 in the person_type table
        (3,'Rebeca Hilson', 40, 2),
         (4,'Johny George', 22, 1),
          (5,'Lewis Boston', 19, 1),
           (6,'Emilia Huston', 31, 1),
            (7,'Selena Johnson', 36, 2),
            (8,'Peter Dwarson' ,22 , 1),
            (9 ,'Mark Hilton' , 25 , 1),
            (10 ,'Sarah Peterson' ,23 ,1),
            (11 ,'Jessica Claron' ,19 ,1);



insert into course (type) values
    ('Math'),
    ('History'),
    ('Sport');

insert into class (name) values
        ('A Class'),
        ('B Class'),
        ('C Class'),
        ('D Class');

insert into course_person (course_id, person_id) values
            (1, 1),
            (2, 3),
            (3, 2),
            (1, 7),
            (1, 10),
            (3, 10),
            (2, 1),
            (1 ,6),
            (1, 4),
            (2,4),
            (3,5),
            (1,5),
            (2,9),
            (3,9),
            (1,8),
            (2, 8),
            (3,6),
            (2 ,10),
            (3,11),
            (1,11);


insert into class_person (class_id, person_id) values
                (1, 2),
                (2, 2),
                (3, 2),
                (1, 3),
                (2, 3),
                (3, 3),
                (2, 7),
                (3,7),
                (1,1),
                (1,4),
                (2,5),
                (2,6),
                (3,8),
                (3,9),
                (4,10),
                (4 ,11);
