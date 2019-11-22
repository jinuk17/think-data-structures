package io.jayden.modern_java_in_action.chap09.template;

public class Database {
    public static Customer getCustomerWithId(int id) {
        return new Customer(id, "name-" + id);
    }
}
