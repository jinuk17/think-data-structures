/**
 *
 * 상태
 *  : 객체는 행위(외부에 노출된는) + 상태(행위를 위한)
 *  : 객체는 프로그램에서의 상태를 적절한 곳에 저장(위치)시켜 프로그램을 수정하는데 도움을 준다.
 *
 *  - 장점
 *      : 인간의 두뇌는 선천적, 후천적으로 상태를 다루는데 익숙하다???
 *  - 단점
 *      : side effect 유발
 *      : 병렬프로그래밍, 동시성? 처리가 어렵다.
 *
 *  : 효과적인 상태 관리를 위해 유사한 상태(동일한 연산에서 사용되고, lifecycle 이 동일한)를 묶어서 관리, 각각의 상태는 별도로 관리
 *
 * 접근
 *  : programming language = accessing stored value + invoking computation, 서로를 통해 설명이 가능하다.
 *  : accessing stored value = 저장된 값을 읽어서 리턴하는 invoking computation
 *  : invoking computation = 어떤 저장되지 않는 메모리 값을 읽는 accessing stored value
 *
 *  : 저장과 계산의 경계? 명확하게 다른사람에게 전달, (저장은 상태, 계산은 행위 ?)
 *  : 객체는 조그마한 메모리를 사용하는 별도의 작은 컴퓨터
 *
 * 직접 접근
 *  : field 에 직접 접근,
 *  : 명확성++ / 유연성--
 *  : 행위로 표현함으로써 의미를 부여할 수 있다. orders = Collections.emptyList()  => clearOrder(); status = 1 => start()
 *  : 접근자 메소드, 생성자에서만 직접 접근 vs class, subclass, package 에서 직접 접근,,,
 *
 * 간접 접근
 *  : 접근자 메소드 사용
 *  : 명확성-- 직접성-- / 유연성++
 *  : 데이터간의 의존이 있을 경우 간접 접근이 용이하다,  {@link io.jayden.implementation_patterns.chapter6_state.Foo#setWidth(int)}
 *
 * 공용 상태
 *  : 같은 데이터 요소를 사용하는 경우 클래스에 필드를 선언해 사용할 수 있다.
 *  : 범위와 생명기간이 같다.
 *  {@link io.jayden.implementation_patterns.chapter6_state.Line}
 *
 * 가변 상태
 *  : 인스턴스 마다 전혀 다른 데이터 요소를 필요로 할때 ,,,
 *  : Map<String, Object> properties = new HashMap<>();
 *  : 가변 상태 -> 공용 상태로 처리할 경우 공용 상태의 변수들의 생명기간이 다를 수도 있다 -> 다형성을 이용해 처리 (Border, Unbordered)
 *
 * 외재 상태
 *  : 내부적으로 상태를 갖지 하고 사용되는 곳에서 객체와 해당 객체와 관련된 상태를 관리한다.
 *
 * 변수
 *  : 접근 scope -> 지역변수, 필드변수, 정적변수
 *  : 접근 제한자 -> public, package, protected, private
 *
 *
 * 지역 변수
 *  : 변수 선언 지점이 속한 범위에서만 접근
 *  : 컬렉터, 카운터, 설명, 재사용, 컬렉션의 원소 등의 용도로 사용가능
 *
 *
 * 필드
 *  - 클래스의 가장 앞이나 가장 뒤에 한꺼번에
 *  - 가장 앞 : 어떤 필드들이 사용될지 문맥을 제공
 *  - 가장 뒤 : 행위가 더 중요, 구현 세부 사항이니 필드는 중요하지 않음을 의미
 *  - final 불변
 *  - 필드 변수 역할
 *      - 도우미 필드 : 여러 메소드에서 파라미터로 전달 받는 경우 생성자를 통해 필드에 저장하고, 메소드에서는 파라미터 대신 도움미 필드를 사용해서 처리
 *      - 플래그 필드 : 객체가 두 가지 다른 방식으로 동작함을 의미한다.
 *      - 전략 필드 : 객체에 연산을 하는 다른 방법이 있음을 나타낸다. 전략이 변경되지 않을 경우 생성자를 통해, 변경될 경우 전략 필드 setter 를 제공하라,
 *      - 상태 : ???
 *      - 부속 : 해당 객체가 소유하는 객체나 데이터 저장
 * 파라미터 ???
 *  : 비전용 변수(필드 혹은 정적 필드)를 사용하지 않고 다른 객체에 상태를 전달하려면 파라미터를 사용해야 한다.
 *    비전용 변수는 클래스 간에 강결함이 발생, 파라미터 사용 권장
 *    동일한 객체의 여러 메소드에서 같은 파라미터를 반복적으로 사용한다면 의존성이 커진다, 파라미터를 생성자를 통해 객체 내부로 옮기면 의존성 감소
 *  - 수집 파라미터
 *      : 복잡한 방식을 통해 결과값을 통합해야 하는 경우 파라미터를 전달해서 결과값을 수집하는 편이 더 직관적인다.
 *      collection 을 던져서 채워달라는 요청이 그냥 collection 을 리턴하는 것과 무슨 차이가 있는지 ,,, ???
 *  - 옵션 파라미터
 *      : 선택적으로 파라미터를 입력할 수 있다, Java 는 키워드, 파라미터 default value 가 지원되지 않아, 옵션 파라미터 수 만큼 메소드를 생성해야 한다.
 *   - 가변 인자 : method(Class... classes) 를 이용하여 임의의 개수의 인자를 사용하여 메소드를 호출 할 수 있다. 가변인자는 항ㅅ
 *  - 파라미터 객체
 *      : 여러개의 파라미터가 함께 여러 메소드에 전달되는 경우 이들을 하나의 객체로 만든다.
 *        여러 메소드의 파라미터에 함께 사용된다는 것은 해당 파라미터들이 밀접한 연관성이 있다는 증거
 * 상수
 *  : 변하지 않는 값을 코드 전반적으로 사용할 때 추후 변경시 상수값만 변경하면 된다.
 *  : 상수 이름을 통해 의미를 전달 할수 있다
 *
 * 역할 제시형 작명
 *  : 변수의 역할을 표현한다.(생명기간, 범위, 타입은 문맥을 통해 알수 있다.)
 *
 * 선언 타입
 *  : 유연성을 위해 가급적 추상적인? 타입으로 정의하라
 *
 * 초기화
 *  : 변수가 사용되기 전의 상태
 *  - 열성적 초기화
 *      : 선언문 또는 생성자
 *      : 변수는 선언문에서 초기화 하는것이 좋다. 선언 타입과 실제 타입을 쉽게 확인할 수 있다.
 *      : 모든 필드를 선언문 또는 생성자에서 초기화 하면 대칭성을 얻는다. 필드가 많지 않으면 적절히 섞어서 사용해도 괜찮다.
 *  - 게으른 초기화
 *      : 초기화 비용이 큰 경우, 사용 시점에 초기화 한다.(성능)
 *
 * */
package io.jayden.implementation_patterns.chapter6_state;