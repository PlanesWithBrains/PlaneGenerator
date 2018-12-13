package sample;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TableOUT {
    private final SimpleStringProperty trip;
    private final SimpleStringProperty time;
    private final SimpleStringProperty plane;

    public TableOUT(int number, Airline airline, LocalDateTime t, Plane p){
        this.trip = new SimpleStringProperty(String.valueOf(number) + " " + airline.name());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "HH:mm" , Locale.US );
        this.time = new SimpleStringProperty(t.format(formatter));
        this.plane = new SimpleStringProperty(p.name);
    }
    public String getPlane(){return plane.get();}
    public String getTrip(){
        return trip.get();
    }
    public LocalDateTime getTime(){
        return LocalDateTime.parse(time.get());
    }

    public void setPlane(Plane p){plane.set(p.name);}
    public void setTrip(int number, Airline airline){
        trip.set(String.valueOf(number) + " " + airline.name());
    }
    public void setTime(LocalDateTime t){
        time.set(t.toString());
    }


    public SimpleStringProperty tripProperty() {return trip;}
    public SimpleStringProperty timeProperty() {return time;}
    public SimpleStringProperty planeProperty() {return plane;}
}
