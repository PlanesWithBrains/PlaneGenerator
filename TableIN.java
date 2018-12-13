package sample;

import javafx.beans.property.*;



public class TableIN {
    static private int count = 0;
    private final SimpleStringProperty trip;
    private final SimpleStringProperty distance;
    private final SimpleStringProperty hight;
    private final SimpleStringProperty plane;
    private final SimpleIntegerProperty number;

    public TableIN(int number, Airline airline, double dist, double high, Plane p){
        count++;
        this.trip = new SimpleStringProperty(String.valueOf(number) + " " + airline.name());
        this.distance = new SimpleStringProperty(String.valueOf(dist));
        this.hight = new SimpleStringProperty(String.valueOf(high));
        this.plane = new SimpleStringProperty(p.name);
        this.number = new SimpleIntegerProperty(count);
    }
    public String getPlane(){return plane.get();}
    public String getTrip(){
        return trip.get();
    }
    public double getDistance(){
        return Double.valueOf(distance.get());
    }
    public double getHight(){
        return Double.valueOf(hight.get());
    }
    public int getCount() {return count;}

    public void setPlane(Plane p){plane.set(p.name);}
    public void setTrip(int number, Airline airline){
        trip.set(String.valueOf(number) + " " + airline.name());
    }
    public void setDistance(double dist){
        distance.set(String.valueOf(dist));
    }
    public void setHight(double hig){
        distance.set(String.valueOf(hig));
    }
    public static void setCount(int n) {count = n;}

    public SimpleStringProperty tripProperty() {return trip;}
    public SimpleStringProperty distanceProperty() {return distance;}
    public SimpleStringProperty hightProperty() {return hight;}
    public SimpleStringProperty planeProperty() {return plane;}
    public SimpleIntegerProperty numberProperty() {return  number;}
}
