package org.example.calculator.lv3;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import org.example.calculator.lv3.constants.OperatorType;
import org.example.calculator.lv3.typeConverter.TypeConverter;

public class ArithmeticCalculator<T extends Number> {

  // 가장 오래된 기록을 지우는 매서드를 위해 Queue 활용
  final private TypeConverter<T> converter;
  final private Queue<T> calHistory;

  public ArithmeticCalculator(TypeConverter<T> converter) {
    calHistory = new LinkedList<>();
    this.converter = converter;
  }


  // 계산을 수행하는 매서드
  // 에러 발생시 -10101010를 반환한다.
  public T calculate(T firstNum, T secondNum, char operator) throws ArithmeticException {
    double temp = switch (operator) {
      case '+' -> OperatorType.PLUS.calculate(firstNum, secondNum);
      case '-' -> OperatorType.MINUS.calculate(firstNum, secondNum);
      case '*' -> OperatorType.MULTIPLY.calculate(firstNum, secondNum);
      case '/' -> OperatorType.DIVIDE.calculate(firstNum, secondNum);
      default -> throw new ArithmeticException("Invalid operator");
    };

    T result = converter.convert(temp);

    this.calHistory.add(result);

    return result;
  }

  // getter, setter 구현사항 문의 필요함
  public String getCalHistory() {
    return calHistory.isEmpty() ? "없음" :
        calHistory.toString().replace("[", "").replace("]", "");
  }

  // 가장 먼저 저장된 cal history를 삭제한다
  // 삭제된 element를 반환한다.
  // 삭제할 수 없을시 -10101010
  public T removeResult() throws NoSuchElementException {
    if (calHistory.isEmpty()) {
      throw new NoSuchElementException("Remove element from empty queue.");
    }
    return calHistory.remove();
  }
}
