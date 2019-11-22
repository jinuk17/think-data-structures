## 7장 행위(Behavior)

- 순서대로 하나씩 수행한다 -> 절차지향

### 제어 흐름(Control Flow)

- 자바 : 인접한 구문은 순선대로 수행된다. -> 절차지향
- 제어 흐름 메커니즘 
    - **조건문**을 이용해 특정상태의 구문만 수행
    - **반복문**을 이용해 반복적으로 코드를 수행 
    - **예외**를 사용해 스택 아래쪽으로 한번에 흐름을 바꿈
- **이러한 메커니즘을 이용해 제어의 흐름을 누구나 쉽게 이해할 수 있게 구현해야한다.**
(클래스의 메소드를 사용, 다른 객체에 제어의 위임)

### 주요 흐름(Main Flow)
- 주요 흐름을 명확히 표현하라.

### 메시지(Message)
- 자바(oop)는 메시지를 이용해 로직을 표현한다. => 인터페이스? 메소드?
- OOP 에서는 객체 간의 메시지를 전달함으로써 제어의 흐름을 표현한다. 

### 선택 메시지(Choosing Message)
- 런타임에 선택이 일어남을 나타내기 위해 다형적 메시지를 사용
```java
class Canvas {
    public void displayShape(final Shape subject, final Brush brush) {
            brush.display(subject);
    }
}
```
- 런타임에 brush 의 타입에 따라 display 를 다르게 수행할 수 있다.
- 장점
    - 명시적 조건문의 사용을 줄일 수 있다. (명시적 조건문은 변경에 취약하다.)
    - 확장이 쉽다.
- 단점
    - 이해하기 어렵다.(명시적 조건문에 비해) 
    - 세부 구현을 알기 위해서 여러 클래스를 살펴봐야 할 수도 있다.
    
- 자신의 의도를 충분히 나타내고, 과도한 사용은 하지 말자.

### Double Dispatch
- 두 가지의 독립적인 차원에서의 변형을 표현하기 위해 ,,, 2개의 선택 메시지를 직렬로 연결

```java
class Canvas {
    public void displayShape2(final Shape shape, final Brush brush) {
        shape.displayWith(brush);
    }
}

class Oval implements Shape {
    @Override
    public void displayWith(Brush brush) {
        brush.displayOval(this);
    }
}

class ScreenBrush implements Brush {
    @Override
    public displayOval(Oval oval) {
        System.out.println(oval);
    }
}
```
- 단점
    - 유연성을 잃는다. (shape 타입이 추가된면 brush 에 메소드 추가 필요)
    - 코드 중복이 발생한다.
    - 첫째 선택 메시지의 수신자 타입이 둘째 선택 메시지의 수신자 메소드에 들어간다. -> 변경될 가능성이 높은 요소를 둘째 선택 메시지의 수신자로 하라.
        - 첫번째 선택 메시지가 변경 -> 첫번째 선택 메시지 1개가 추가 -> 두번째 선택 메시지 모든 구현 객체에  메시지가 추가된다.
        - 두번째 선택 메시지가 변경 -> 두번째 선택 메시지 1개가 추가 

### Decomposing (Sequencing) Message
- 여러단계의 복잡한 알고리즘이 있다면, 관련된 단계들을 모으고 이를 수행하기 위한 메시지를 보낸다.
- 이름을 **잘** 지어야 한다.
```java
class Wish {
    public void wish(Long itemId, Long userId) {

        checkValidItem(itemId);

        final WishItem wishItem = wishItem(itemId, userId);

        wishLogger.log(wishItem);
    }
}
```

### 되돌림 메시지(Reversing Message)
- 대칭성을 이용한 가독성 향상 예제,,,
```java
class Computer {
    void compute() {
        this.input();
        helper.process(this);
        this.output();
    }
    void compute1() {
        this.input();
        this.process(helper);
        this.output();
    }   
    
    void process(Helper helper) {
        helper.process(this);
    }      

}
```
- 되돌림 메시지가 과도하게 사용된다면 구현의 이동을 의심하라,,,
- 미학적 느낌, 코드 퀄리티 

### 초청 메시지(Inviting Message)
- 하위 클래스에서 어떤 연산이 변경될 것이라 예상될 경우 적당한 메시지를 통해 변경 여지를 알려라,,,
- 기본 구현이 없는 경우 추상 메소드로 선언하여 초청 메시지임을 분명히 하라,,,

### 설명 메시지(Explaining Message)
- 프로그래머의 의도를 좀더 명확하게 전달하기 위해,,,
- 연산, 구현의 설명이 아니라 프로그래머의 의도를 설명,,,

```java
class A {
    void highlight(Rectangle rec) {
        reverse(rec);
    }   
}
```

### 예외 흐름(Exceptional Flow)
- 수행 빈도가 낮고, 수정 빈도도 낮다. 
- 주요 흐름의 명료성을 훼손하지 않는 범위 내에서 명료해야한다. 
- 주요 흐름을 최대한 명료하게 하라. (위시 예제) 

### 보호절(Guard Clause)
```java
class A {
    void initialize1() {
        if(!isInitialize()) {
            
        }
    }
    void initialize2() {
        if(isInitialize()) return;  
    }
   
}
```
- `initialize1` 는 내부 코드를 볼때 상위 조건을 기억하고 고려해야한다.
- `initialize2` 는 최초 보호절을 통해 명확히 초기화되지 않았음을 인지할 수 있다.

- if-then-else 는 동등한 중요성을 갖고 있을 경우
- 보호절은 한 쪽의 제어 흐림이 중요한 경우

- 중첩된 조건문에서 효율적이다.
```java
public class Computer {

    void compute1() {
        Server server = getServer();
        if (server != null) {
            Client client = server.getClient();
            if(client != null) {
                Request request = client.getRequest();
                if(request != null) {
                    processRequest(request);
                }
            }
        }
    }

    void compute2() {
        Server server = getServer();
        if (server == null) return;

        Client client = server.getClient();
        if(client == null) return;

        Request request = client.getRequest();
        if(request == null) return;

        processRequest(request);
    }

    void compute3() {
        Optional.ofNullable(getServer())
                .map(Server::getClient)
                .map(Client::getRequest)
                .ifPresent(this::processRequest);
    }
}
```

- 반복문에서의 `continue` 도 보호절

### 예외(Exception)
- 여러 함수 호출을 걸쳐서 제어의 흐름을 바꾸는 경우를 표현할때 유용
- 예외를 발견하는 쪽에서는 예외를 던지고, 처리 가능한 곳에서만 예외를 받아라, 그 사이의 모든 코드에서 예외를 체크하고 전달하면 지저분하다.
- 예외는 비용이 들어간다.(일종의 설계상 누수, 사이드 이펙트)
    - 제어의 흐름을 따라가기 어렵다.
    - 코드를 읽고 이해하기 어렵다.

### 체크 예외(Checked Exception)
- checked exception : 예외를 처리하도록 강제하기 위한 Java feature. Checked Exception 은 컴파일러에 의해 반드시 처리하도록 강제된다.

### 예외 전달(Exception Propagation)
- 하위 수준의 예외를 상위 수준의 예외로 포장하라.