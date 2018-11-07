package sample;

import java.sql.Time;

enum Airline{
    SBI,//S7,
    AFL,//Аэрофлот,
    NWS,//Nordwind,
    AZO, //Азимут
    PBD,//Победа,
    QTR,//QatarAirways,
    UAE//EmiratesAirline
}
enum Direction{
    North,East,West,South,NorthWest,NorthEast,SouthWest,SouthEast
}
/**
 *
 * @author Дмитрий Соловьев
 */
public class Flight{
    Airline carrier;
    int number;
    Plane plane;
    Direction direction;
    Time time;
    boolean status;
    int distance;
    int hight;

    Flight(Plane P, Airline NameCompany, int numb, Direction dir, Time t, boolean state, int dist, int hig){ //для посадки
        this.plane = P;
        this.carrier = NameCompany;
        this.number = numb;
        this.direction = dir;
        this.time = t;
        this.status = state;
        this.distance = dist;
        this.hight = hig;
    }

    Flight(Plane P, Airline NameCompany, int numb, Direction dir, Time t){ //для взлета
        this.plane = P;
        this.carrier = NameCompany;
        this.number = numb;
        this.direction = dir;
        this.time = t;
        this.status = true;
        this.distance = 0;
        this.hight = 0;
    }
    String getCarrier(){
        return carrier.name();
    }

}
