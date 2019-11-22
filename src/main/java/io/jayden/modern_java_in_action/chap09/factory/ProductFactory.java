package io.jayden.modern_java_in_action.chap09.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProductFactory {

    public static Product createProduct(final String name) {
        switch (name) {
            case "loan" :
                return new Loan();
            case "stock" :
                return new Stock();
            case "bond" :
                return new Bond();
            default :
                throw new RuntimeException("No such product " + name);
        }
    }

    interface Product{}
    static class Loan implements Product{}
    static class Stock implements Product{}
    static class Bond implements Product{}


    public static void main(String[] args) {
        final Product loan = createProduct("loan");
        final Product stock = createProduct("stock");

        /*
        * Map 과 Supplier 를 통해서 팩토리 패턴을 표현할 수 있다.
        * */

        Map<String, Supplier<Product>> map = new HashMap<>();
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);

        final Map<String, Supplier<Product>> productFactory = Collections.unmodifiableMap(map);
        final Product loan1 = productFactory.get("loan").get();
    }
}
