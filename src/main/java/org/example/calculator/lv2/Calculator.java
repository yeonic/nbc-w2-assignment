package org.example.calculator.lv2;

import java.util.LinkedList;
import java.util.Queue;

public class Calculator {

  // 가장 오래된 기록을 지우는 매서드를 위해 Queue 활용
  private Queue<Integer> calHistory;

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

  // getter
  // collection에 있는 모든 element를 comma-seperated string으로 반환
  public String getCalHistory() {
    // collection이 비어있을 때에는 "없음" 출력
    return calHistory.isEmpty() ? "없음" :
        calHistory.toString().replace("[", "").replace("]", "");
  }

  // setter
  // calHistory를 새로운 객체로 변경
  public void setCalHistory(LinkedList<Integer> newHistory) {
    calHistory = newHistory;
  }

  // 가장 먼저 저장된 calHistory element를 삭제한다
  // 삭제된 element를 반환한다.
  // 삭제할 수 없을시 -10101010
  public int removeResult() {
    if (calHistory.isEmpty()) {
      return -10101010;
    }
    return calHistory.remove();
  }
}
