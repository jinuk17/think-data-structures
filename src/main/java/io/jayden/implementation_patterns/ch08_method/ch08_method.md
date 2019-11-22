## 8장 메소드(Method)

* 로직은 하나의 덩어리가 아닌 여러 개의 메소드로 구성
* 가독성
    * 주요 흐름을 표현할 수 있다.(덜 중요하거나 복잡한 로직을 감춠 수 있다.)
    * 로직간의 연관성을 표현할 수 있다(응집력)
    * 메소드 이름으로 의도를 표현할 수 있다.
* 중복 제거, 재사용
* 메소드를 나눌 때 **메소드의 크기, 목적, 이름** 등을 고려해야 한다.
* 너무 작게 많이 만들어도 문제(메소드의 파악이 힘들다), 너무 적어도 문제(중복, 유연성이 떨어짐)

---
### 조합 메소드(Composed Method)
* 추상화 수준이 비슷한 메소드 호출을 하나의 메소드로 구성한다.
```java
class Foo {
    void compute() {
        input();
        flags |= 0x0080;
        output();
    }
}

class Bar {
   void compute() {
        input();
        process();
        output();
    }
    void process() {
        flags |= 0x0080;
    }
}
```
* ~~메소드 호출 때문에 생기는 성능 저하문제~~
* 메소드 크기
    * 메소드의 길이 -> 5~15줄? 읽기 좋은 메소드의 길이는?
    * 제어 구조의 중첩(조건문, 반복문)
    * 저자의 경험상으로는 상대적으로 짧은 메소드 단위로 코드를 구성할 때 코드 이해가 가장 쉬웠다.
    * 특화(specialization) : 적절한 크기로 메서드를 작성하면 상위 클래스의 특정 기능을 재정의 하기 위해 (메소드가 너무 크면)상위클래스의 코드를 복사하거나, (메소드가 너무 작으면)여러개의 메소드를 오버라이드 하지 않아도 된다. 

* 메소드를 구성할 때는 추측이 아닌 사실에 근거하라, 일단 동작하는 코드를 만들고 구성 방식을 결정하라. -> TDD???
* 애매할 때는 일단 전체를 절차적으로 구현하고 고민해라.
 
### 의도 제시형 이름(Intention-Revealing Name)
* 이름을 통해 메소드의 의도를 전달하라,(구현 전략이 아닌,,,)
* 호출하는 입자에서 생각하라.
* 기존에 구현된 이름을 참조하라(JDK, spring,,,)

### 메소드 가시성(Method Visibility)
* 외부 노출 인터페이스 vs 변경에 대한 유연성 
* 저자의 전략은 가급적 가시성을 낮추는 것 (심사숙고해서 노출할지 판단하라,,,)
* 가시성을 표현하는 방법(java)
    * public - 전체 노출
    * package - 패키지 내에서만 노출
    * protected - 하위 클래스에만 노출
    * private - 객체 내부에만 노출
* 가시성을 수정하는 것은 비용이 크다, 가장 제한적인 낮은 수준의 가시성을 선택한 후 필요에 따라 가시성을 높여라 ,,,

* final - 재정의 불가 
* static - 정적 메소드, 클래스 메소드(인스턴스 없이 가시성에 맞게 접근가능)

### 메소드 객체(Method Object)
* 복잡하게 꼬여 있는 메소드를 읽기 쉽고 명확하면서도 세부 구현 전달이 쉽도록 바꿔준다.
* 메소드 -> 클래스
* 메소드에서 사용한 파라미터, 지역변수, 필드 -> 새 객체의 필드
* 메소드에서 사용한 파라미터, 필드 -> 새 객체의 생성자 파라미터로 정의
* 메소드 -> 새 객체에 동일한 이름의 메소드 생성
* 리팩토링하기 용의하다.

### 오버라이드(Overridden Method)
* 변형 메소드를 명확하게 표현
* abstract 메소드 또는 final 이 아닌 메소드
* super.method() 를 통해 상위메소드를 호출할 수 있으나, 같은 이름의 메소드에서만 호출하는 것이 좋다.

### 오버로드(Overloaded Method)
* 같은 메소드 이름에 서로 다른 파라미터를 사용 -> 이 메소드를 사용할 수 있는 다양한 포맷이 존재함을 의미.
* 같은 메소드 이름에 다른 수의 파라미터를 사용
* 파라미터 타입, 수만 다를 뿐 같은 연산을 수행해야 한다.

### 메소드 반환 타입(Method Return Type)
* 프로시저(void), 함수인지 구별할 수 있다.
* 함수를 작성할 때는 의도를 나타내는 반환 타입을 사용해라.
* 의도를 들어낼 수 있는 가장 추상적인 타입을 사용하라.

### 메소드 주석(Method Comment)
* 코드만으로 분명하지 않은 정보는 주석을 사용해 전달하라.
* 주석을 작성하고 코드와 주석 간 일관성을 유지하기 위해 노력이 든다 -> 이런 노력이 정당화되 수 있는 경우에만 주석을 사용하라. (주석도 유지보수가 되어야 한다.)
* 자동화된 테스트를 사용하면 주석으로 표현할 수 없는 내용을 전달할 수 있다. (자동화된 테스트도 유지보수가 되어야 한다.)

### 도우미 메소드(Helper Method)
* 당장 관련도가 떨어지는 세부 구현을 숨기고,
* 메소드 이름을 통해 프로그래머의 의도를 나타냄.
* 가독성 향상을 위함.
* private 로 선언
* 중복 코드 제거

### 디버그 출력 메소드(Debug Print Method)
* toString() 은 프로그래머에게 유용한 객체의 정보를 위해 사용한다. 

---

### 변환(Conversion)
* 객체의 변환을 어떻게 처리할 것인가?
* A -> B

### 변환 메소드(Conversion Method)
* 유사한 타입의 제한적인 경우 A 에 메소드 추가   

### 변환 생성자(Conversion Constructor)
* B 에 생성자를 통해 변환
* `File(String name)`, `URL(String spec)`, `StringReadStream(String contents)`

---

### 생성(Creation)
* 객체 생성

### 완결 생성자(Complete Constructor)
* 객체는 연산을 하기 위해 정보를 필요로 한다 -> 연산을 위한 선결 조건을 전달하라.(생성자 파라미터)

`new Rectangle(0, 0, 50, 200);`

* 생성자 파라미터는 여러개의 설정 메소드를 사용해서 좀 더 유연성을 가져갈 수 있다. -> 필수 조건의 인자를 파악할 수 없다.
* 모든 생성자가 동일한 하나의 생성자를 사용해서 모든 초기화를 하도록 하라. 

### 공장 메소드(Factory Method)
* 정적 메소드를 이용해서 객체 생성을 나타낼 수 있다.
* 추상타입 반환 가능
* 의도가 담긴 별도의 이름을 가질 수 있다.
* 객체 생성외의 다른 의도가 있을 경우에만 사용하라.(복잡한 작업, 런타임에 결정되는 하위클래스 반환)

### 내부 공장(Internal Factory)
* 게으른 초기화를 사용하는 경우 -> 게으른 초기화 로직이 복잡할 경우 내부 공장을 사용하는 편이 낫다.

---

### 컬렉션 접근자 메소드(Collection Accessor Method)
* 문제점 : 취득 메소드를 통해 컬렉션을 제공하면 컬렉션 데이터가 변경에 노출되 위험하다.
* 수정불가능한 컬렉션으로 변경 후 노출한다.
```java
List<Book> getBooks() {
    return Collections.unmodifiableList(books);
}
```
* 취득 메소드 대신 외부에 의미 있는 인터페이스는 노출한다.
```java
void addBook(Book arrival) {
    books.add(arrival);
}
int bookCount() {
    return books.size();
}
```
* Iterator 반환 -> 원소 삭제를 제외한 컬렉션 수정을 막을 수 있다.
 

### 불린 설정 메소드(Boolean Setting Method)
* 불린 상태 마다 메소드르 제공해서 인터페이스를 더욱 명확하게 할 수 있다.
```java
class Foo {
    void valid() {}
    void invalid() {}

    void toggle() {}
}
```
* 어떤 경우는 설정 메소드(setter)가 더 나을 수 있다.
```java
class Foo {
    void foo() {
        if(/* ... boolean expression ... */) {
            b.valid();
        }else {
            b.invalid();
        }
    }
}
``` 

### 질의 메소드(Query Method)
* "be"동사나 "have"동사를 사용하여 표현한다. 
* 어떤 객체가 다른 객체의 상태에 의존적인 로직을 많이 가지고 있다면, 로직의 위치에 문제가 있다는 신호.
```java
class Main {
    void doSomething1(){
        if (widget.isVisible())
            widget.doSomething();
        else
            widget.doSomethingElse();
    }
    
    void doSomething2(){
        widget.visible();
    }
}

class Widget {

    void visible() {
        if (this.isVisible())
            this.doSomething();
        else
            this.doSomethingElse();
    }   
}
```

### 동등성 메소드(Equality Method)
* 동등성을 비교해야 할 경우 equals(), hashcode() 를 구현해라.
* 동등한 객체는 같은 해쉬값을 가져야하므로 hashcode() 구현에는 equals() 에서 사용한 데이터만 사용해야 한다.
```java
public boolean equals(Object other) {
    if (! other instanceof Instrument)
        return false;

    Instrument instrument= (Instrument) other;
    return getSerialNumber().equals(instrument.getSerialNumber());
}

public int hashCode() {
    return getSerialNumber.hashCode();
}
```

* 붎변개체와 팩토리 메소드를 사용함으로써 동등성을 문제를 피할 수 있다.
```java

static Instrument create(String serialNumber) {
    if (cache.containsKey(serialNumber))
        return cache.get(serialNumber);

    Instrument result= new Instrument(serialNumber);
    cache.put(serialNumber, result);
    return result;
}
```

### 취득 메소드(Getting Method)
* getter
* 데이터와 로직을 함께 배치한다는 원칙(객체지향)에 입각하면 가시성이 높은 취득 메소드는 적절하지 않다.
* 취득 메소드를 제공해야 할때는 가급적 필요한 로직을 데이터 쪽으로 옮겨라.  
* 내부 취득 메소드는 게이른 초기화나 캐쉬를 구현할 때 유용.

### 설정 메소드(Setting Method)
* setter
* 인터페이스에 설정 메소드를 포함시키면, 내부 구현을 노출시키게 된다.
```java
paragraph.setJustification(Paragraph.CENTERED);
...
paragraph.centered();
```
* 내부적으로 설정 메소드를 사용하는 하는 경우 : 의존적인 정보를 업데이트 하는 경우
```java
private void setJustification(...) {
    ...
    redisplay();
}
```

### 안전한 복사(Safe Copy)
* 취득 메소드(getter), 설정 메소드(setter)를 사용하면 엘리어스 문제가 발생할 수 있다.
* 취득 메소드(getter), 설정 메소드(setter)에서 복사본을 만들어 엘리어스 문제를 회피할 수 있다.
```java
List<Book> getBooks() {
    List<Book> result = new ArrayList<Book>();
    result.addAll(books);
    return result;
}

void setBooks(List<Book> newBooks) {
    books = new ArrayList<Book>();
    books.addAll(newBooks);
}
```
* 객체 복사 비용 때문에 성능상 이슈가 있을 수 있다.
* 가급적 구현의 해심 기법으로의 사용은 피하자 -> 불변 객체, 적합한 조합 메소드를 통해 해결하자.