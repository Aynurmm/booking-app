package az.academy.turing.enums;

public enum FlightQuery {
    insert_flight("insert into flight (id, from_city,to_city,timestamp,available_seats) values (?,?,?,?,?)"),
    get_flightByIdd("select * from flight where id=?"),
    get_allFlights("select*from flight"),
    delete_flightById("delete from flight where id=?"),
    update_flightById("update flight set from_city=?, to_city=?,timestamp=?,available_seats=? where id=?");

    private final String query;

    FlightQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
