package io.jayden.modern_java_in_action.chap09.template;

import java.util.function.Consumer;

/*
* 변경 포인트를 람다 표현이 가능한 인터페이스로 매개변수로 정의한다. 전달인자로 받는다.
* */
public class OnlineBanking2 {
    public void processCustomerForLambda(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

    public static void main(String[] args) {

        final OnlineBanking onlineBanking = new OnlineBanking() {
            @Override
            protected void makeCustomerHappy(Customer c) {
                System.out.println(c.getName() + " is happy.");
            }
        };
        onlineBanking.processCustomer(10);


        final OnlineBanking2 onlineBanking2 = new OnlineBanking2();
        onlineBanking2.processCustomerForLambda(10, c -> System.out.println(c.getName() + " is happy."));

    }
}
