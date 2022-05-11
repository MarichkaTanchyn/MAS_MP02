package withAttribute;

import java.time.LocalDateTime;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        House h1 = new House(
                1,
                "House_1",
                LocalDateTime.of(2002,9,24,0,0),
                new HouseAddress("5th Avenue", 22),
                Set.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14)
        );
        House h2 = new House(
                2,
                "House_2",
                LocalDateTime.of(2000,9,20,0,0),
                new HouseAddress("5th Avenue", 24),
                Set.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14)
        );

        System.out.println("Zatrudnieni pracownicy do oddania domu (ma być pusta lista)");
        System.out.println(h1.getEmployments().toString());
        Employee e1 = new Employee(1,"Mid", "Medowyj", "Ohrona");
        Employee e2 = new Employee(2,"Midyna", "Medowa", "Sprzątarka");
        Employment empl1 =new Employment(1,LocalDateTime.of(1999,1,1,0,0),8,h1,e1);
        Employment empl2 =new Employment(2,LocalDateTime.of(1999,1,1,0,0),8,h1,e2);

        System.out.println("\nZatrudniono 2 osoby dla budynku 1");
        System.out.println(h1.getEmployments().toString());
        System.out.println("Pracownik 2");
        System.out.println(e2.getEmployments().toString());

        h1.removeEmployment(empl1);

        System.out.println("usunięto employment dla house id: " + h1.getId() + " and employee id: " + e1.getId());

        System.out.println(h1.getEmployments().toString());
        System.out.println(e1.getEmployments().toString());
    }
}
