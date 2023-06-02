@Entity @Table(name = "employee") 
public class Employee {

@OneToOne
@JoinColumn(name = "address_id")
private Address address;
  
@ManyToOne
@JoinColumn(name = "department_id")
private Department department;
  
@ManyToMany
@JoinTable(
        name = "employee_department",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "department_id")
)
private List<Department> departments;

}
