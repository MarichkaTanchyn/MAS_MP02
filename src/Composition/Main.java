package Composition;

import java.time.LocalDateTime;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        HousingComplex housingComplex1 = new HousingComplex(1, LocalDateTime.now().minusDays(10));
        HousingComplex housingComplex2 = new HousingComplex(2, LocalDateTime.now().minusDays(20));

        House.createHouse(housingComplex1, 1, "h1", LocalDateTime.now().minusDays(5), new HouseAddress("address1", 1), Set.of(1, 2,3,4,5,6,7,8,9,10,11));
        House.createHouse(housingComplex1, 2, "h2", LocalDateTime.now().minusDays(4), new HouseAddress("address2", 2), Set.of(1, 2,3,4,5,6,7,8,9,10,11));
        House.createHouse(housingComplex2, 3, "h3", LocalDateTime.now().minusDays(3), new HouseAddress("address3", 3), Set.of(1, 2,3,4,5,6,7,8,9,10,11));

        System.out.println(housingComplex1.getHouses());
        System.out.println(housingComplex2.getHouses());

        HousingComplex.removeComplex(housingComplex2);
        System.out.println(House.getHouses());
    }
}
