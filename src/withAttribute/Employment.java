package withAttribute;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Employment {
    private long id;
    private LocalDateTime employmentDate;
    private int hoursPerWeek;

    private static final Set<Employment> employments = new HashSet<>();

    private House house;
    private Employee employee;

    public Employment(long id, LocalDateTime employmentDate, int hoursPerWeek, House house, Employee employee) {
        setId(id);
        setEmploymentDate(employmentDate);
        setHoursPerWeek(hoursPerWeek);
        setHouse(house);
        setEmployee(employee);
        Employment.addEmployment(this);
    }

    private static boolean pairIsNotUnique(House house, Employee employee) {
        return Employment.employments
                .stream()
                .anyMatch(
                        t -> t.getHouse() == house &&
                                t.getEmployee() == employee);
    }

    private static void addEmployment(Employment employment) throws IllegalArgumentException {
        if (employment == null) {
            throw new IllegalArgumentException("Employment cannot be null.");
        }

        boolean alreadyAdded = Employment.employments.stream().anyMatch(p -> p.getId() == employment.getId());
        if (alreadyAdded) {
            throw new IllegalArgumentException("Employment with id " + employment.getId() + " has already been added.");
        }

        Employment.employments.add(employment);
    }

    private static void removeEmployment(Employment employment) throws IllegalArgumentException {
        if (employment == null) {
            throw new IllegalArgumentException("Employment cannot be null.");
        }

        boolean removed = Employment.employments.remove(employment);
        if (!removed) {
            throw new IllegalArgumentException("Employment with id " + employment.getId() + " was not removed.");
        }
    }

    private void removeAll() {
        removeEmployment(this);
        removeHouse();
        removeEmployee();
    }

    public void removeHouse() {
        if (getHouse() != null) {
            House tmp = this.house;
            this.house = null;
            tmp.removeEmployment(this);
        }
    }

    public void removeEmployee() {
        if (getEmployee() != null) {
            Employee tmp = this.employee;
            this.employee = null;
            tmp.removeEmployment(this);
        }
    }

    public void setId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id cannot be less than or equal to 0.");
        }
        this.id = id;
    }

    public void setEmploymentDate(LocalDateTime employmentDate) {
        if (employmentDate == null) {
            throw new IllegalArgumentException("Employment date cannot be null");
        }
        if (employmentDate.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Date of employment cannot be set to date in future");
        }
        this.employmentDate = employmentDate;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be less than or equal to 0.");
        }
        this.hoursPerWeek = hoursPerWeek;
    }

    public void setHouse(House house) {
        if ((getHouse() == null) && house != null) {
            if (pairIsNotUnique(house, this.employee)) {
                throw new IllegalArgumentException("Pair is not unique.");
            }
            this.house = house;
            house.addEmployment(this);
        }
    }

    public void setEmployee(Employee employee) {
        if ((getEmployee() == null) && employee != null) {
            if (pairIsNotUnique(this.house, employee)) {
                throw new IllegalArgumentException("Pair is not unique.");
            }
            this.employee = employee;
            employee.addEmployment(this);
        }
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getEmploymentDate() {
        return employmentDate;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public static Set<Employment> getEmployments() {
        return Collections.unmodifiableSet(employments);
    }

    public House getHouse() {
        return house;
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public String toString() {
        return "Employment{" +
                "id=" + id +
                ", employmentDate=" + employmentDate +
                ", hoursPerWeek=" + hoursPerWeek +
                ", house=" + house +
                ", employee=" + employee.getId() +
                '}';
    }
}
