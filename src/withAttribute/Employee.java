package withAttribute;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Employee {
    private long id;
    private String name;
    private String surname;
    private String profession;

    private static final Set<Employee> employees = new HashSet<>();

    private final Set<Employment> employments;

    public Employee(long id, String name, String surname, String profession) {
        employments = new HashSet<>();
        setId(id);
        setName(name);
        setSurname(surname);
        setProfession(profession);
        Employee.addEmployee(this);
    }

    public void addEmployment(Employment employment) {
        if (!(employment == null) && ! employments.contains(employment)){
            employments.add(employment);
            employment.setEmployee(this);
        }
    }

    public void removeEmployment(Employment employment) {
        if (employments.contains(employment)){
            employments.remove(employment);
            employment.removeHouse();
            employment.removeEmployee();
        }
    }


    private static void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        boolean alreadyAdded = Employee.employees.stream().anyMatch(p -> p.getId() == employee.getId());
        if (alreadyAdded) {
            throw new IllegalArgumentException("Employee with id " + employee.getId() + " has already been added.");
        }

        Employee.employees.add(employee);
    }
    private static void removeEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }

        boolean removed = Employee.employees.remove(employee);
        if (!removed) {
            throw new IllegalArgumentException("Employee with id " + employee.getId() + " was not removed.");
        }
    }

    public void setId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id cannot be less than or equal to 0.");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().equals("")) {
            throw new IllegalArgumentException("First name cannot be null or empty.");
        }
        this.name = name;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.trim().equals("")) {
            throw new IllegalArgumentException("Surname cannot be null or empty.");
        }
        this.surname = surname;
    }

    public void setProfession(String profession) {
        if (profession == null || profession.trim().equals("")) {
            throw new IllegalArgumentException("Profession cannot be null or empty.");
        }
        this.profession = profession;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getProfession() {
        return profession;
    }

    public static Set<Employee> getEmployees() {
        return Collections.unmodifiableSet(employees);
    }

    public Set<Employment> getEmployments() {
        return Collections.unmodifiableSet(employments);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", profession='" + profession + '\'' +
                ", employments=" + employments +
                '}';
    }
}
