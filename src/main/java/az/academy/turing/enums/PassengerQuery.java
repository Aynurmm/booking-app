package az.academy.turing.enums;

public enum PassengerQuery {
    insert_passenger("insert into passenger (id,first_name,last_name,login,password)values(?,?,?,?,?)"),
    getPassenger_ById("select * from passenger where id=?"),
    get_allPassengers("select * from passenger"),
    delete_passengerById("delete from passenger where id=?"),
    update_passengerById("update passenger set first_name=?,last_name=?,login=?,password=? where id=?");
    private final String query;

     PassengerQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
