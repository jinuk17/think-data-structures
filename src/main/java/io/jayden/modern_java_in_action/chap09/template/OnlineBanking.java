package io.jayden.modern_java_in_action.chap09.template;

import java.util.function.Consumer;

/*
* 템플릿 메소드 패턴
* 전반적인 처리 개요를 제시한 다음 일부 알고리즘을 유연하게 변경할 수 있도록 함.
* */
public abstract class OnlineBanking {
    public void processCustomer(int id) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }

    protected abstract void makeCustomerHappy(Customer c);
}
