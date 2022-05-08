package qualified;

import Composition.HousingComplex;

import java.util.*;

public class School {
    private long id;
    private int numberOfStudents;
    private int numberOfTeachers;

    private static final Set<School> schools = new HashSet<>();


    private final Map<Long, House> houses;


    public School(long id, int numberOfStudents, int numberOfTeachers) {
        houses = new HashMap<>();
        setId(id);
        setNumberOfStudents(numberOfStudents);
        setNumberOfTeachers(numberOfTeachers);
        School.addSchool(this);
    }


    private static void addSchool(School school) {
        if (school == null) {
            throw new IllegalArgumentException("School cannot be null.");
        }

        boolean alreadyAdded = School.schools.stream().anyMatch(e -> e.getId() == school.getId());
        if (alreadyAdded) {
            throw new IllegalArgumentException("School with id " + school.getId() + " has already been added.");
        }

        School.schools.add(school);
    }

    private static void removeSchool(School school) {
        if (school == null) {
            throw new IllegalArgumentException("School cannot be null.");
        }

        boolean removed = School.schools.remove(school);
        if (!removed) {
            throw new IllegalArgumentException("School with id " + school.getId() + " was not removed.");
        }
    }

    public void setNumberOfStudents(int numberOfStudents) {
        if (id <= 0) {
            throw new IllegalArgumentException("Number of students cannot be less than or equal to 0.");
        }
        if (numberOfStudents <= numberOfTeachers) {
            throw new IllegalArgumentException("Number of students cannot be less than or equal to number of teachers.");
        }
        this.numberOfStudents = numberOfStudents;
    }

    public void setNumberOfTeachers(int numberOfTeachers) {
        if (id <= 0) {
            throw new IllegalArgumentException("Number of teachers cannot be less than or equal to 0.");
        }
        if (numberOfTeachers >= numberOfStudents) {
            throw new IllegalArgumentException("Number of Teachers cannot be more than or equal to number of students.");
        }
        this.numberOfTeachers = numberOfTeachers;
    }


 /// to house all
    public void addHouse(House house) {
        if (houses.containsValue(house)){
            houses.put(house.getId(), house);
            house.setSchool(this);
        }

    }

    public void removeHouse(House house) {
        if (houses.containsValue(house)){
            houses.remove(house.getId());
            house.setSchool(null);
        }
    }

    public Map<Long, House> getHouses() {
        return Collections.unmodifiableMap(houses);
    }

    public long getId() {
        return id;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }


    public void setId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id cannot be less than or equal to 0.");
        }
        this.id = id;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", numberOfStudents=" + numberOfStudents +
                ", numberOfTeachers=" + numberOfTeachers +
                '}';
    }
}
