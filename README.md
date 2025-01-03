# Week2 계산기 구현 과제

## About project

| 구분  | 내용                                                         |
|-----|------------------------------------------------------------|
| 목적  | Java의 문법을 이해하고, 객체지향 원칙을 지켜서 간단한 계산기 프로그램을 제작한다.           |
| 기간  | 2025.1.2 ~ 2025.1.3                                        |
| 설명  | 이 프로젝트는 LV. 1~3 순으로 진행되었고, 단계별로 Java의 요소를 추가하는 방식으로 구현되었다. |
| JDK | Amazon Corretto 17.0.13 - aarch64                          |

<br/><br/>

## Features

### Lv. 1

<img src="https://github.com/yeonic/nbc-w2-assignment/blob/main/resources/lv1.png" width="50%" alt="lv1">

- 별도의 클래스 분리 없이 Procedural Programming 스타일로 구현됨
- 두 개의 Integer와 Operator Character를 입력받아 계산을 수행함
- 매 계산 수행시마다 종료 여부를 검사하고, exit을 입력시 프로그램이 종료됨

### Lv. 2

<img src="https://github.com/yeonic/nbc-w2-assignment/blob/main/resources/lv2.png" width="50%" alt="lv2">

- Calculator Class 구현
    - 계산 함수 이식
    - 계산 후 Queue를 사용하여 계산 히스토리 저장
    - 가장 오래된 기록 삭제하는 메서드
    - Queue를 교체할 수 있는 매서드(Setter)


- App Class 구현
    - Calculator Class를 활용한 main 매서드

### Lv. 3

<img src="https://github.com/yeonic/nbc-w2-assignment/blob/main/resources/lv3.png" width="50%" alt="lv3">

- ArithmeticCalculator를 제네릭 클래스로 구현
    - double과 integer 지원(extends Number)


- Stream & Lambda를 이용한 기록 필터링 조회
- 기능 변경에 따른 App main 수정

<br/><br/><br/>

## Structure

[LV.3](#lv-3)의 ArithmeticCalculator 클래스를 Generic으로 구현하기 위해 도입한 구조에 대한 설명이다.
<br/><br/>

### Generic을 지원하는 calculate함수

#### enum OperatorType

- calculate method를 OperatorType enum에서 정의
    - abstract method를 override하여 연산자별로 다르게 구현.
    - `Number` 객체, `doubleValue()` method를 활용해서 int와 double 지원

<details>
<summary>코드 예시</summary>

```java
public enum OperatorType {
  PLUS {
    @Override
    public double calculate(Number a, Number b) {
      return a.doubleValue() + b.doubleValue();
    }
  };

  // ...MINUS, MULTIPLY, DIVIDE 생략

  // enum에 abstract method를 두어 enum의 각 요소들이 override하도록 함
  // Number로 parameter의 타입을 추상화함.
  public abstract double calculate(Number a, Number b);
}

```

</details>
<br/><br/>

#### class ArithmeticCalculator

- 연산자에 따른 분기를 처리함.
    - 연산자에 맞는 `enum OperatorType`의 method를 호출
    - [convert](#typeconverter) 매서드를 호출하여 T 타입으로 변환
  <details>
  <summary>코드 예시</summary>

  ```java
  // 계산을 수행하는 매서드
  // 에러 발생시 ArithmeticException을 Throw.
  public T calculate(T firstNum, T secondNum, char operator) throws ArithmeticException {
    double temp = switch (operator) {
      case '+' -> OperatorType.PLUS.calculate(firstNum, secondNum);
      case '-' -> OperatorType.MINUS.calculate(firstNum, secondNum);
      case '*' -> OperatorType.MULTIPLY.calculate(firstNum, secondNum);
      case '/' -> OperatorType.DIVIDE.calculate(firstNum, secondNum);
      default -> throw new ArithmeticException("Invalid operator");
    };
  
    this.calHistory.add(temp);
  
    return converter.convert(temp);
  }
  ```

  </details>

<br/><br/><br/>

### Integer와 Double을 T로 수렴해주는 TypeConverter

#### TypeConverter

- `interface TypeConverter`를 implement
- 타입에 맞는 타입 변환 지원
  <details>
  <summary>코드 예시</summary>

  ```java
  public class IntConverter implements TypeConverter<Integer> {
  
    // int인 경우, 소수점 아래는 버리고, 정수 부분만 남긴다.
    @Override
    public Integer convert(double value) {
      return (int) value;
    }
  }
  ```

  </details>

<br/><br/>

#### class ArithmeticCalculator

  <details>
  <summary>Property로 `converter`를 가짐</summary>

  ```java
  public class ArithmeticCalculator<T extends Number> {

  // Converter to be injected
  final private TypeConverter<T> converter;
  private Queue<Double> calHistory;

  // TypeConverter는 inject 받도록 함
  //  client가 타입을 결정하도록 하여 제어를 역전시킴.
  public ArithmeticCalculator(TypeConverter<T> converter) {
    calHistory = new LinkedList<>();
    this.converter = converter;
  }
}
  ```

  </details>

  <details>
  <summary> Method의 반환형을 T로 수렴할 수 있게 도와줌 </summary>

  ```java
  // 가장 먼저 저장된 cal history를 삭제한다
// 삭제된 element를 반환한다.
// 비어있는 기록에 삭제 요청시 NoSuchElementException
public T removeResult() throws NoSuchElementException {
  if (calHistory.isEmpty()) {
    throw new NoSuchElementException("Remove element from empty queue.");
  }
  return converter.convert(calHistory.remove());
}
  ```

  </details>

