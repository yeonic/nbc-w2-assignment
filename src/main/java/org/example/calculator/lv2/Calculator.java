package org.example.calculator.lv2;

import java.util.LinkedList;
import java.util.Queue;

public class Calculator {

  // 가장 오래된 기록을 지우는 매서드를 위해 Queue 활용
  final private Queue<Integer> calHistory;

  public Calculator() {
    calHistory = new LinkedList<>();
  }

  // 계산을 수행하는 매서드
  // 에러 발생시 -10101010를 반환한다.
  public int calculate(int firstNum, int secondNum, char operator) {
    int result = -10101010;

    switch (operator) {
      case '+':
        result = firstNum + secondNum;
        break;
      case '-':
        result = firstNum - secondNum;
        break;
      case '*':
        result = firstNum * secondNum;
        break;
      case '/':
        if (secondNum == 0) {
          System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
          break;
        }
        result = firstNum / secondNum;
        break;
      default:
        System.out.println("지원되지 않는 연산자입니다.");
        break;
    }

    this.calHistory.add(result);

    return result;
  }

  // getter, setter 구현사항 문의 필요함
  public String getCalHistory() {
    return calHistory.toString().replace("[", "").replace("]", "");
  }

  // 가장 먼저 저장된 cal history를 삭제한다
  // 삭제된 element를 반환한다.
  public int removeResult() {
    return calHistory.remove();
  }
}
