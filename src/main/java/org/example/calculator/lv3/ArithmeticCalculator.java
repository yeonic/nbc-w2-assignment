package org.example.calculator.lv3;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import org.example.calculator.lv3.constants.OperatorType;
import org.example.calculator.lv3.typeConverter.TypeConverter;

public class ArithmeticCalculator<T extends Number> {

  // 가장 오래된 기록을 지우는 매서드를 위해 Queue 활용
  final private TypeConverter<T> converter;
  final private Queue<Double> calHistory;

  public ArithmeticCalculator(TypeConverter<T> converter) {
    calHistory = new LinkedList<>();
    this.converter = converter;
  }


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

  public String getCalHistory() {
    List<T> result = calHistory.stream().map(converter::convert).toList();
    return result.isEmpty() ? "없음" :
        result.toString().replaceAll("[\\[,\\]]", "");
  }

  // 가장 먼저 저장된 cal history를 삭제한다
  // 삭제된 element를 반환한다.
  // 비어있는 기록에 삭제 요청시 NoSuchElementException
  public T removeResult() throws NoSuchElementException {
    if (calHistory.isEmpty()) {
      throw new NoSuchElementException("Remove element from empty queue.");
    }
    return converter.convert(calHistory.remove());
  }

  public String getHistoryLessThan(double num) {
    List<T> result = calHistory.stream().filter(t -> t > num)
        .map(converter::convert).toList();
    return result.isEmpty() ? "없음" : result.toString().replaceAll("[\\[,\\]]", "");
  }
}
