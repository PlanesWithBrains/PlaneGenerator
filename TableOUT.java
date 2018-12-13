package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TableOUT {
    static private int count = 0;
    private final SimpleStringProperty trip;
    private final SimpleStringProperty date;
    private final SimpleStringProperty time;
    private final SimpleStringProperty plane;
    private final SimpleIntegerProperty number;

    public TableOUT(int number, Airline airline, LocalDateTime t, Plane p){
        count++;
        this.trip = new SimpleStringProperty(String.valueOf(number) + " " + airline.name());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "HH:mm" , Locale.US );
        this.time = new SimpleStringProperty(t.format(formatter));
        formatter = DateTimeFormatter.ofPattern( "dd-MM-yyy");
        this.date = new SimpleStringProperty(t.format((formatter)));
        this.plane = new SimpleStringProperty(p.name);
        this.number = new SimpleIntegerProperty(count);
    }
    public String getPlane(){return plane.get();}
    public String getTrip(){
        return trip.get();
    }
    public LocalTime getTime(){ return LocalTime.parse(time.get()); }
    public LocalDate getDate() {return LocalDate.parse(time.get());}
    public int getCount() {return count;}

    public void setPlane(Plane p){plane.set(p.name);}
    public void setTrip(int number, Airline airline){
        trip.set(String.valueOf(number) + " " + airline.name());
    }
    public void setTime(LocalTime t){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "HH:mm" , Locale.US );
        time.set(t.format(formatter));
    }
    public void setDate(LocalDate d) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
        date.set(d.format(formatter));
    }
    public static void setCount(int n) {count = n;}

    public SimpleStringProperty tripProperty() {return trip;}
    public SimpleStringProperty timeProperty() {return time;}
    public SimpleStringProperty planeProperty() {return plane;}
    public SimpleStringProperty dateProperty() {return date; }
    public SimpleIntegerProperty numberProperty() {return  number;}
}
