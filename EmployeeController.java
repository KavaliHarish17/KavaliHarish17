@RestController 
@RequestMapping("/employees") 
public class EmployeeController {

@Autowired
private EmployeeService employeeService;

@GetMapping
public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
}
  
@PostMapping
public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
    Employee createdEmployee = employeeService.addEmployee(employee);
    return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
}
  
@GetMapping(params = "department")
public List<Employee> getEmployeesByDepartment(@RequestParam("department") String department) {
    return employeeService.getEmployeesByDepartment(department);
}
  
private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);


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
}
