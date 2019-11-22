# 10장 발전하는 프레임워크(Evolving Frameworks)

- 호환성을 깨뜨리는 업그레이드에 대한 영향을 줄이거나 피하는 방법,,,

## 어플리케이션 수정 없이 프레임워크 수정하기(Changing Frameworks without Changing Applications)

- 기존 클라이언트 코드는 계속 동작하면서 프레임워크를 지속적으로 발전시켜야 한다.
- 호환성을 유지하기 위해서는 복잡도가 올라간다.
- 최대한 호환성을 유지, 호환성이 깨지더라도 최소화
- 호환성(프레임워크 유지 비용) vs 단순성(클라이언트 개발 비용) 균형있게 맞추자

## 호환성 없는 업그레이드(Incompatible Upgrades)

- 병렬 아키텍쳐
    - 새로운 API 를 제공함과 동시에 기존 API 를 deprecation 후 서서히 제거한다.(자바 컬렉션 - Vector, Enumerators)
    - 패키지를 사용하면 import 문 변경만으로 업그레이드 가능
- API 와 구현을 동시에 바꾸지 않는다.
    - 인터페이스 또는 구현을 바꾼 중간 릴리즈 코드를 제공(적은 변화를 통해 상호간에 익숙해질 시간을 제공)
- 자동으로 클라이언트 코드를 업그레이드 해주는 도구 제공
- 단순 편집기의 검색/치환을 통한 기존 코드 업그레이드
- 업그레이드 문서 제공

## 호환성을 유지하는 업그레이드(Encouraging Compatible Change)

- 클라이언트 코드는 가급적 프레임워크의 변하지 않는 부분에만 의존적이어야 한다.
- 프레임워크는 **가급적 눈에 보이는 세부사항은 숨기고 변화하지 않을 부분만 노출**한다면, 유연성을 얻을 수 있다.
- 전방 호환성(forward compatibility) : 이후 버전에서 만들어진 입력 또는 포맷이 이전 버전에서 처리할 수 있는 경우
```java
    UserClientV1.getClient(oldToken) -> UserClientV1.getClient(newToken)
```
- 후방 호환성(backward compatibility) : 이전 버전에서 만들어진 입력 또는 포맷을 이후 버전에서 처리할 수 있는 경우
```java
    UserClientV1.getClient(oldToken) -> UserClientV2.getClient(oldToken)
```

### 라이브러리 클래스(Library Class)
- 간단한 파라미터를 취하는 프로시저 호출로 표현
- `Collecionts` 클래스
- 정적 메소드로 제공
- 표현할 수 있는 개변과 변형의 수가 제한

### Objects
- 클라이언트가 프레임워크의 바뀌지 않을 부분에만 의존적으로 코드를 작성하게 하는 것

#### 사용 스타일(Style of use)
- 인스턴스화(instantiation), 설정(configuration), 구현(implementation) 스타일 지원
- 인스턴스화 
    - `new ServerSocket()` 생성 
    - public method 를 호출하는 방식으로 사용
    - 데이터의 변형만 필요로 하는 경우 사용
- 설정
    ```java
        // 구현
        Comparator<Author> byFirstName = new Comparator<Author>() {
            public int compare(Author book1, Author book2) {
                return book1.getFirstName().compareTo(book2.getFirstName());
            }
        };
        // 설정
        SortedSet<Author> sorted= new TreeSet<Author>(byFirstName);
    ```

    - `Comparator`
    - 데이터뿐 아니라 로직의 변형도 지원, 인스턴스화 보다 유연
    - 계속해서 같은 인터페이스를 사용해야 하므로 프레임워크를 발전시키는데 제한
    - 실질적으로 표현할 수 있는 변형의 종류에도 제한 ???
- 구현
    - 어떤 로직이라도 사용 가능
    - 프레임워크 설계 변경을 가장 크게 제한
- JUnit 
    - `JUnitCore` run <- **라이브러리 메소드**  
    - `JUnitCore` <- **인스턴스화** 지원
    - `@Test`, `@Before`, `@After` <- **설정** 
    - `@RunWith` <- **구현**
    
#### 추상화(Abstraction)

- 구현 스타일을 사용하려면 추상화된 개념을 **인터페이스(Interface)**로 전달할지 **상위클래스(Superclass)**로 전달할지 결정해야 한다. 

---
- 인터페이스
    - 세부 사항을 가급적 적게 노출
    - 메소드 추가시 모든 클라이언트 구현이 동작하지 않는다. (Java8 default method)
        -> 버전 인터페이스(하위 인터페이스 생성) 복잡도 증가 ,,,
    - 다중 구현 가능 
- 상위클래스
    - 세부사항을 나타낼 수 있고, 상위 클래스에 메소드를 추가해도 동작한다. (abstract method 는 인터페이스와 동일함,,,)
    - 단일 상속
    - 상위 클래스의 `public`, `protect` 메소드, 필드 노출 -> 가급적 세부 사항을 적게 노출하라.
    - 필드는 항상 `private` 으로 선언하라, 혹시나 제공해야되면 `getter`를 제공하라
    - `final class` 상속 제한
    - 때로는 패키지 이름을 통해서 프래임워크 내부의 경계를 표현하기도 한다. `org.eclipse.jdt`, `org.eclipse.jdt.internal`
    
#### Creation

- 구상 클래스의 인스턴스화 방법
- 일반성, 복잡도, 사용의 용이성, 발전의 용이성 등을 모두 고려

---

- 생성금지(No Creation)
    - 가장 단순하고 유용성이 떨어지는 옵션,,,
- 생성자(Constructors)
    - 명확하다,
    - 클래스 이름, 객체 생성에 필요한 파라미터, 클래스가 속한 패키지 등 대부분의 요소를 수정하지 않아도 된다면 합리적인 선택 ,,,   
- 정적 공장(Static Factories)
    - 클라이언트 : 객체 생성 복잡도 증가
    - 프레임워크 : 미래 설계에 대한 유연성 증가
    - 추상타입 반환 가능
    - 의도가 담긴 별도의 이름을 가질 수 있다.
- 공장 객체(Factory Object)
    - 정적 메소드보다 유연성을 얻지만, 코드를 읽기 어려워진다.
    - 함께 사용된느 클래스들을 생성하는데 유용(윈도우용 위젯, 리눅스용 위젯)

#### Methods
- **가급적 세부사항은 적게 노출하라**
- 클라이언트가 내부 자료구조에 의존하면 프레임워크 수정이 어려워진다. 
- 설정 메소드 -> 클라이언트가 해결하려는 문제를 메소드 이름에 반영 `setVisible(boolean) -> visible(), invisible()`
- 메소드 이름에 내부 구현 대신 역할을 반영하도록 하자 
- 이미 공개된 메소드에 파라미터를 추가할 경우 호환성 유지를 위해 기본값을 제공하라.
```java

public TestResult run(Class... classes) {
    ....run tests in classes...
}
public void run(TestResult result, Class... classes) {
    ...run tests in classes...
}

public TestResult run(Class... classes) {
    TestResult result= new TestResult();
    run(result, classes);
    return result;
}
```