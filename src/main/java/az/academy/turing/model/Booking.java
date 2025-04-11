package az.academy.turing.model;

public class Booking {
    private int id;
    private int flight_id;
    private int passenger_id;


    public Booking(int id, int flight_id, int passenger_id) {
        this.id = id;
        this.flight_id = flight_id;
        this.passenger_id = passenger_id;
    }

    public Booking() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", flight_id=" + flight_id +
                ", passenger_id=" + passenger_id +
                '}';
    }
}
