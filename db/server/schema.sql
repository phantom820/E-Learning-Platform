SET datestyle = "ISO, DMY";

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
       

-- CREATE resource schema
CREATE schema resource;
                          
-- CREATE past papers table 
CREATE TABLE resource.past_paper(paper_id SERIAL PRIMARY KEY,grade SMALLINT,subject VARCHAR(100),paper_no SMALLINT,
								 type VARCHAR(20),province VARCHAR(20),date DATE,paper_name VARCHAR(100),paper_url varchar(255));
								 
CREATE TABLE resource.past_paper_memo(memo_id SERIAL PRIMARY KEY, grade SMALLINT,subject VARCHAR(100),paper_no SMALLINT,
								 type VARCHAR(20),province VARCHAR(20),date DATE,memo_name VARCHAR(100),memo_url VARCHAR(255),paper_id INT UNIQUE,  
								 CONSTRAINT fk_paper FOREIGN KEY(paper_id) REFERENCES resource.past_paper(paper_id));
