package basic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Resident {
    private long id;
    private String name;
    private String surname;
    private int apartmentNumber;

    // class extent
    private static final Set<Resident> residents = new HashSet<>();

    private House house;

    public Resident(long id, String name, String surname, int apartmentNumber,House house) {
        setId(id);
        setName(name);
        setSurname(surname);
        setApartmentNumber(apartmentNumber);
        setHouse(house);
        Resident.addResident(this);
    }

    // Association methods
    public void setHouse(House house) {
        if (this.house == house) return;

        if (this.house == null && house != null) {
            this.house = house;
            house.addResident(this);
        } else if (this.house != null && house == null) {
            House tmp = this.house;
            this.house = null;
            tmp.removeResident(this);
        } else if (this.house != null && house != null) {
            House tmp = this.house;
            this.house = null;
            tmp.removeResident(this);

            this.house = house;
            house.addResident(this);
        }
    }//zmienic dużo powtarzającego sie kodu

    public House getHouse() {
        return house;
    }

    private static void addResident(Resident resident) throws IllegalArgumentException {
        if (resident == null) {
            throw new IllegalArgumentException("House cannot be null.");
        }

        boolean alreadyAdded = Resident.residents.stream().anyMatch(a -> a.getId() == resident.getId());
        if (alreadyAdded) {
            throw new IllegalArgumentException("House with id " + resident.getId() + " has already been added.");
        }

        Resident.residents.add(resident);
    }

    private static void removeResident(Resident resident) throws IllegalArgumentException {
        if (resident == null) {
            throw new IllegalArgumentException("Resident cannot be null.");
        }

        boolean removed = Resident.residents.remove(resident);
        if (!removed) {
            throw new IllegalArgumentException("Resident with id " + resident.getId() + " was not removed.");
        }
    }


    public void setApartmentNumber(int apartmentNumber) {
        if (apartmentNumber < 0) {
            throw new IllegalArgumentException("Apartment Number cannot be negative value.");
        }
        this.apartmentNumber = apartmentNumber;
    }

    public void setId(long id) throws IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative value.");
        }

        this.id = id;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.trim().equals("")) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public void setSurname(String surname) throws IllegalArgumentException {
        if (surname == null || surname.trim().equals("")) {
            throw new IllegalArgumentException("Surname cannot be null or empty.");
        }
        this.surname = surname;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public static Set<Resident> getResidents() {
        return Collections.unmodifiableSet(residents);
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

    @Override
    public String toString() {
        return "Resident{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", apartmentNumber=" + apartmentNumber +
                ", house=" +house +
                '}';
    }
}
