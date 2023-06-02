sql
CREATE TABLE department (
    id INT PRIMARY KEY,
    departmentname VARCHAR(50)
);

CREATE TABLE address (
    id INT PRIMARY KEY,
    street VARCHAR(100),
    street2 VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(50)
);

CREATE TABLE employee (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    address_id INT,
    gender VARCHAR(10),
    contact VARCHAR(20),
    department_id INT,
    FOREIGN KEY (address_id) REFERENCES address(id),
    FOREIGN KEY (department_id) REFERENCES department(id)
);
