package az.academy.turing.enums;

public enum BookingQuery {
    insert_booking("insert into booking (id, flight_id, passenger_id,numberOfSeats) values (?,?,?,?)"),
    get_bookingById("select * from booking where id=?"),
    get_allBookings("select * from booking"),
    delete_bookingById("delete from booking where id=?"),
    update_bookingById("update booking set flight_id=?, passenger_id=?,numberOfSeats=? where id=?");

    private final String query;

    BookingQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
