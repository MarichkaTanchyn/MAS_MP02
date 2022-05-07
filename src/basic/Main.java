package basic;

import java.time.LocalDateTime;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Resident maria = new Resident(1,"Maria", "Tanchyn",4,null);
        Resident max = new Resident(2,"Michael", "Did",3,null);

        House h1 = new House(
                1,
                LocalDateTime.of(2000,1,20,0,0),
                new HouseAddress("5th Avenue", 22),
                Set.of(1,2,3,4,5,6,7,8,9,10)
        ,maria);
        h1.addApartment(16);
        h1.removeApartment(16);

        House h2 = new House(
                2,
                "house",
                LocalDateTime.of(2002,9,24,0,0),
                new HouseAddress("5th Avenue", 22),
                Set.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14),max
        );


        System.out.println("\nMieszkańcy z przypisamymi mieszkaniami");
        System.out.println(maria);
        System.out.println(max);

        maria.setHouse(h2);
        System.out.println("\nTransferred Maria to house2");
        System.out.println(maria);
        System.out.println(max);

        h2.removeResident(maria);
        System.out.println("\nRemoved Maria from house 2 in :");
        System.out.println(h2.getResidents());
    }
}
