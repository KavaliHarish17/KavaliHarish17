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


2) Join between employee and address table (One-to-One):

java
@Entity
@Table(name = "employee")
public class Employee {

    // ...

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    // getters and setters
}


3) Join between employee and department table (One-to-Many):

java
@Entity
@Table(name = "employee")
public class Employee {

    // ...

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // getters and setters
}


4) Join between employee and department table (Many-to-Many):

java
@Entity
@Table(name = "employee")
public class Employee {

    // ...

    @ManyToMany
    @JoinTable(
            name = "employee_department",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private List<Department> departments;

    // getters and setters
}


Now, let's proceed with the rest service implementation:

1) Fetch all employee details with address and department:

java
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}


2) Add data in employee (including address and department):

java
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
}


3) Fetch employee data by department:

java
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(params = "department")
    public List<Employee> getEmployeesByDepartment(@RequestParam("department") String department) {
        return employeeService.getEmployeesByDepartment(department);
    }
}


4) Implement logger and exception handling:

java
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
        try {
            return employeeService.getAllEmployees();
        } catch (Exception e) {
            logger.error("Error occurred while fetching employees: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch employees");
        }
    }

    // Rest of the methods with similar exception handling
}
