package sample;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalTime;

public class TableOUT {
    private final SimpleStringProperty trip;
    private final SimpleStringProperty time;
    private final SimpleStringProperty plane;

    public TableOUT(int number, Airline airline, LocalTime t, Plane p){
        this.trip = new SimpleStringProperty(String.valueOf(number) + " " + airline.name());
        this.time = new SimpleStringProperty(t.toString());
        this.plane = new SimpleStringProperty(p.name);
    }
    public String getPlane(){return plane.get();}
    public String getTrip(){
        return trip.get();
    }
    public LocalTime getTime(){
        return LocalTime.parse(time.get());
    }

    public void setPlane(Plane p){plane.set(p.name);}
    public void setTrip(int number, Airline airline){
        trip.set(String.valueOf(number) + " " + airline.name());
    }
    public void setTime(LocalTime t){
        time.set(t.toString());
    }


    public SimpleStringProperty tripProperty() {return trip;}
    public SimpleStringProperty timeProperty() {return time;}
    public SimpleStringProperty planeProperty() {return plane;}
}
