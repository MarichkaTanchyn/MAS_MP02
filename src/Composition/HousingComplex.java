package Composition;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class HousingComplex {
    private long id;
    private LocalDateTime openingDate;

    private final Set<House> houses;
    private static final Set<House> allHouses = new HashSet<>();

    private static final Set<HousingComplex> housingComplexes = new HashSet<>();

    public HousingComplex(long id, LocalDateTime openingDate) {
        houses = new HashSet<>();
        setId(id);
        setOpeningDate(openingDate);
        addHouseComplex(this);
    }

    public void addHouse(House house) {
        if (house == null) {
            throw new IllegalArgumentException("Cannot add null house.");
        }

        if (!houses.contains(house)) {
            if (allHouses.contains(house)) {
                throw new IllegalArgumentException("This house is already connected with another Plane.");
            }

            houses.add(house);
            allHouses.add(house);
            house.setHousingComplex(this);
        }
    }

    public void removeHouse(House house) {
        if (house == null) {
            throw new IllegalArgumentException("Cannot remove house which does not exist.");
        }

        this.houses.remove(house);
        HousingComplex.allHouses.remove(house);
    }


    public Set<House> getHouses() {
        return Collections.unmodifiableSet(houses);
    }

    private static void addHouseComplex(HousingComplex housingComplex) {
        if (housingComplex == null) {
            throw new IllegalArgumentException("Cannot add null part.");
        }


        boolean alreadyExists = housingComplexes.stream().anyMatch(p -> p.getId() == housingComplex.id);
        if (alreadyExists) {
            throw new IllegalArgumentException("Part with such ID already exists");
        }

        HousingComplex.housingComplexes.add(housingComplex);
    }

    public static void removeComplex(HousingComplex housingComplex) {
        if (housingComplex == null) {
            throw new IllegalArgumentException("Housing complex cannot be null.");
        }

        HousingComplex.allHouses.removeAll(housingComplex.houses);
        House.removeHousesFrom(housingComplex);
        boolean removed = HousingComplex.housingComplexes.remove(housingComplex);

        if (!removed) {
            throw new IllegalArgumentException("Plane with id " + housingComplex.getId() + " was not removed.");
        }
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public static Set<HousingComplex> getComplexes() {
        return Collections.unmodifiableSet(housingComplexes);
    }

    public void setId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id cannot be less than or equal to 0.");
        }
        this.id = id;
    }

    public void setOpeningDate(LocalDateTime openingDate) {
        if (openingDate == null) {
            throw new IllegalArgumentException("Opening date cannot be null");
        }
        if (openingDate.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Date of opening cannot be set to date in future");
        }
        this.openingDate = openingDate;
    }

    @Override
    public String toString() {
        return "HousingComplex{" +
                "id=" + id +
                ", openingDate=" + openingDate +
                '}';
    }
}
