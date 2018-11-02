package sample;

enum Airline{
    SBI,//S7,
    AFL,//Аэрофлот,
    NWS,//Nordwind,
    AZO, //Азимут
    PBD,//Победа,
    QTR,//QatarAirways,
    UAE//EmiratesAirline
}
/**
 *
 * @author Дмитрий Соловьев
 */
public class Flight{
    Airline carrier;
    int number;
    Plane plane;
    boolean status;
    Flight(Plane P, Airline NameCompany, int numb, boolean state){
        this.plane = P;
        this.carrier = NameCompany;
        this.number = numb;
        this.status = state;
    }
    String getCarrier(){
        return carrier.name();
    }

}
