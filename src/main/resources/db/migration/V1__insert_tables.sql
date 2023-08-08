create table if not exists person_type(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(255)
);


create table if not exists person(
      id BIGINT PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(255),
      age INTEGER NOT NULL CHECK (age >= 0) ,
      type_id BIGINT,
      FOREIGN KEY(type_id) REFERENCES person_type(id)

);

create table if not exists course(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(255) UNIQUE

);

create table if not exists course_person (
    course_id BIGINT,
    person_id BIGINT,
    FOREIGN KEY (course_id) REFERENCES course(id),
    FOREIGN KEY (person_id) REFERENCES person(id),
    PRIMARY KEY (course_id, person_id)
);

create table if not exists class (
        id BIGINT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(255)
);

create table if not exists class_person (
    class_id BIGINT,
    person_id BIGINT,
    FOREIGN KEY (class_id) REFERENCES class(id),
    FOREIGN KEY (person_id) REFERENCES person(id),
    PRIMARY KEY (class_id, person_id)
);




