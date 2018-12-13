package sample;

import java.util.ArrayList;

/**
 *
 * @author Дмитрий Соловьев
 */
public class Plane {
    String name;
    int lengthRunway;
    int countPassangers;

    Plane(String Name, int LengthRun, int CountPass){
        this.name = Name;

        this.lengthRunway = LengthRun;
        this.countPassangers = CountPass;
    }
    static ArrayList<Plane> getPlanes(){
        ArrayList<Plane> temp = new ArrayList<>();
        //Имя, вес, кр. скорость, длина разбега, число мест
        temp.add(new Plane("A310",1900,280));
        temp.add(new Plane("A320",2090,180));
        temp.add(new Plane("A330",2500,440));
        temp.add(new Plane("A380",2050,853));
        temp.add(new Plane("Boeing-737",2241,189));
        temp.add(new Plane("Boeing-747",2180,660));
        temp.add(new Plane("Boeing-757",2550,279));
        temp.add(new Plane("Boeing-767",3400,375));
        temp.add(new Plane("Boeing-777",3700,550));
        temp.add(new Plane("Ил-62",3150,186));
        temp.add(new Plane("Ил-86",2800,350));
        temp.add(new Plane("Ту-154",2300,180));

        return temp;

    }
}