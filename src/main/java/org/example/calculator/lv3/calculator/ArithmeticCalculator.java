package org.example.calculator.lv3;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import org.example.calculator.lv3.constants.OperatorType;
import org.example.calculator.lv3.typeConverter.TypeConverter;

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

  // getter
  // collection에 있는 모든 element를 space-seperated string으로 반환
  public String getCalHistory() {
    List<T> result = calHistory.stream().map(converter::convert).toList();
    return result.isEmpty() ? "없음" :
        result.toString().replaceAll("[\\[,\\]]", "");
  }

  // setter
  // calHistory를 새로운 객체로 변경
  public void setCalHistory(LinkedList<Double> newHistory) {
    calHistory = newHistory;
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

  // num보다 큰 기록을 문자열로 보여준다
  // 조건에 맞는 결과가 없는 경우 "없음이 출력된다"
  public String getHistoryLessThan(double num) {
    List<T> result = calHistory.stream().filter(t -> t > num)
        .map(converter::convert).toList();
    return result.isEmpty() ? "없음" : result.toString().replaceAll("[\\[,\\]]", "");
  }
}
