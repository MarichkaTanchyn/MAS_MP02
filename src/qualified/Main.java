package qualified;

import java.time.LocalDateTime;
import java.util.Set;

public class Main {
    public static void main(String[] args) {


        House h1 = new House(
                1,
                "House",
                LocalDateTime.of(2002,9,24,0,0),
                new HouseAddress("5th Avenue", 22),
                Set.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14),null);

        School school = new School(1,50,10);
        school.addHouse(h1);
        System.out.println(school.getHouses().toString());

        h1.setId(23);
        System.out.println(school.getHouses().toString());

        school.removeHouse(h1);
        System.out.println(school.getHouses().toString());

    }
}
