package io.jayden.modern_java_in_action.chap09.stragegy;

/**
 * The type Validator.
 * 전략 객체를 사용하는 클라이언트
 */
public class Validator {

    private final ValidationStrategy validation;

    public Validator(final ValidationStrategy v) {
        this.validation = v;
    }

    public boolean validate(final String s) {
        return validation.execute(s);
    }

    public static void main(String[] args) {
        /*
        * 기존 전략패턴 사용 예시
        * */
        final Validator validator1 = new Validator(new IsAllLowerCase());
        final Validator validator2 = new Validator(new IsNumeric());

        final boolean r1 = validator1.validate("saSDFOIEWKFOWdlfdf");
        final boolean r2 = validator2.validate("1212");
        System.out.println(r1);
        System.out.println(r2);

        /*
         * 람다를 이용한 전략패턴
         * */
        final Validator lowerCaseValidator = new Validator(s -> s.matches("[a-z]+"));
        final Validator numericValidator = new Validator(s -> s.matches("\\d+"));

        final boolean r3 = lowerCaseValidator.validate("saSDFOIEWKFOWdlfdf");
        final boolean r4 = numericValidator.validate("1212");
        System.out.println(r3);
        System.out.println(r4);

        /*
        * 결론 : Strategy Interface 에 대한 구현체를 별도의 클래스로 생성할 필요 없이 람다 구문으로 대체 가능하다.
        * (Strategy Interface 메소드가 1개일 경우?)
        * */
    }
}
