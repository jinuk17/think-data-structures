# 9장 컬렉션(Collections)

## 메타포(Metaphors)
- 컬렉션은 여러 값을 가진 변수 
- 컬렉션은 객체
- 컬렉션은 인자로 전달 가능한 객체로 구현 되므로, 변수 값이 아닌 변수 자체를 인자로 전달하는 참조 호출(call by reference)의 효과을 얻을 수 있다.

## Issue
- 선언은 가장 일반적인 인터페이스, 사용은 가장 구체적인 구현 클래스
- 크기, 순서, 독자성(uniqueness), 접근, 성능 등을 고려하여 컬렉션을 선택  


## Interface

### Array
- 다른 컬렉션들과 다른 프로토콜
- 고정된 크기
- 시간, 공간 모든 면에서 효율적

### Iterable
- 순차 열람 가능
- 원소가 지워질 위험이 있음 -> 수정 불가하도록 랩핑, 재정의, 안전한 복사본 등   

### Collection
- Iterable 상속
- 추가, 삭제, 검색, 크기 메소드 지원

### List
- Collection 상속
- 순서
- 인덱스 접근 

### Set
- Collection 상속
- 중복 없음
- 순서 없음

### SortedSet
- Set 상속
- `Comparator` 에 의해 순서 보장
- 중복 없음

### Map
- 임의의 객체를 키로 사용해서 원소저장
- 키는 중복 없음
- 원소 데이터는 중복 가능
- 순서 없음

## Implementations

### Collection
- ArrayList : `contains` 및 `contains` 를 사용하는 메소드들이 크기에 비례하여 연산시간이 커진다.
- HashSet :  `contains` 및 `contains` 를 사용하는 메소드 성능은 좋으나, 중복 지원이 안됨.

### List
|                | 원소 접근              | 원소 추가, 제거        |
| :--------------| -------------------:|:---------------:|
| ArrayList      | 빠름                  | 느림             |
| LinkedList     | 느림                  | 빠름             | 

### Set
|                | 원소 접근               | 원소 추가, 제거    | 순서보장    |
| :--------------| -------------------:|:---------------:|:---------------:|
| HashSet        | 빠름                  | 빠름             |      X    |
| LinkedHashSet  | 빠름                  | 느림(30%정도)     |       O(입력순서) |
| TreeSet        | 빠름                  | 느림(N logN)        |       O(Comparator) |


### Map
|                | 원소 접근               | 원소 추가, 제거    | 순서보장    |
| :--------------| -------------------:|:---------------:|:---------------:|
| HashMap        | 빠름                  | 빠름             |      X    |
| LinkedHashMap  | 빠름                  | 느림             |       O(입력순서) |
| TreeMap        | 빠름                  | 느림(N logN)     |       O(Key Comparator) |

## Collections
- 유틸리티 클래스

### 검색(Searching)
- `Collections.binarySearch(list, element)`
- `indexOf()` 는 O(N) 
- **정렬**되어 있는 collection 은 O(logN) 에 비례하여 검색 가능
- 임의 접근 가능한 리스트에 대해서만 향상된 성능 제공

### 정렬(Sorting)
- `reverse(list)` 순서를 거꾸로 정렬
- `shuffle(list)` 임의의 순서로 정렬
- `sort(list)`, `sort(list, comparator)` 오름차순 정렬
- `ArrayList`, `LinkedList` 는 정렬 성능이 거의 동일 -> 내부적으로 배열로 복사해서 처리

### 수정 불가능한 컬렉션(Unmodifiable Collections)
- `Collections.unmodifiableCollection(list)`
- 컬렉션 수정을 막아준다. 내부적으로 수정 가능한 method 를 재정의함 `throw new UnsupportedOperationException()`

### 단일 원소 컬렉션(Single-Element Collections)
- `Collections.singleton()`
- 단일 원소를 갖는 컬렉션
- 수정 불가능한 컬렉션

### 무원소 컬렉션(Empty Collections)
- `Collections.emptyList()`

---

## Extending Collections
- 상속 보단 위임을 사용하라

