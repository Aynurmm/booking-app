package az.academy.turing.model;

import java.time.LocalDateTime;

public class Flight {
    private int id ;
    private String from_city;
    private String to_city;
    private LocalDateTime timestamp;
    private int available_seats;
    private int seats;


    public Flight(int id, String from_city, String to_city, LocalDateTime timestamp, int available_seats) {
        this.id = id;
        this.from_city = from_city;
        this.to_city = to_city;
        this.timestamp = timestamp;
        this.available_seats = available_seats;
    }
    public Flight(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom_city() {
        return from_city;
    }

    public void setFrom_city(String from_city) {
        this.from_city = from_city;
    }

    public String getTo_city() {
        return to_city;
    }

    public void setTo_city(String to_city) {
        this.to_city = to_city;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", from_city='" + from_city + '\'' +
                ", to_city='" + to_city + '\'' +
                ", timestamp=" + timestamp +
                ", available_seats=" + available_seats +
                '}';
    }
}
