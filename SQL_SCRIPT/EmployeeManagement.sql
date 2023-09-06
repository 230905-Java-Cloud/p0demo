--I want two tables with a 1-to-many relationship to use in my p0demo
--I can accomplish this relationship with primary key/foreign key relationships
--foreign keys of one table always point to the primary key of another table

DROP TABLE IF EXISTS employees, roles;


--roles table
CREATE TABLE roles(
	role_id serial PRIMARY KEY, --the primary key is a UNIQUE IDENTIFIER for each record (row)
	--serial is an AUTO INCREMENTING int - perfect for primary keys, which must be unique
	role_title TEXT UNIQUE NOT NULL,
	role_salary int check(role_salary > 20000) --no salaries under 20,000 allowed
); 

--employees table
CREATE TABLE employees(
	employee_id serial PRIMARY KEY,
	first_name TEXT NOT NULL, 
	last_name TEXT NOT NULL,
	role_id_fk int REFERENCES roles(role_id)
	--"this is a foreign key, and the primary key it references is 'role_id' from the 'roles' table"
);


--insert some role data
INSERT INTO roles(role_title, role_salary)
VALUES ('Manager', 100000), ('Cashier', 40000), ('Fry Cook', 50000), ('Marketing Director', 100000);

SELECT * FROM roles;


--insert some employee data
INSERT INTO employees(first_name, last_name, role_id_fk)
values('Eugene', 'Krabs', 1), ('Squidward', 'Tentacles', 2), 
('Spongebob', 'Squarepants', 3), ('Sheldon', 'Plankton', 4);

SELECT * FROM employees;



