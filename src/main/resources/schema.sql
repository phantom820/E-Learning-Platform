
-- CREATE student TABLE
CREATE TABLE student(student_id  SERIAL PRIMARY KEY,name VARCHAR(255),surname VARCHAR(255),
                  email VARCHAR(255) NOT NULL UNIQUE,contact VARCHAR(10) NOT NULL UNIQUE,password VARCHAR(255) NOT NULL);
                  
-- CREATE schoolS TABLE 
CREATE TABLE school(school_id VARCHAR(255) NOT NULL PRIMARY KEY,name VARCHAR(255),
                    type VARCHAR(255),province VARCHAR(255),suburb VARCHAR(255), postal_code VARCHAR(10));

-- CREATE student details
CREATE TABLE student_details(id SERIAL PRIMARY KEY,gender VARCHAR(6),race VARCHAR(50),grade VARCHAR(15),
                          birth_date DATE,student_id INT UNIQUE, school_id VARCHAR(255),
						  CONSTRAINT fk_student FOREIGN KEY(student_id) REFERENCES student(student_id),
                          CONSTRAINT fk_school FOREIGN KEY (school_id) REFERENCES  school(school_id));

