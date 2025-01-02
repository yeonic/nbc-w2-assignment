package org.example.calculator.lv2;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Calculator cal = new Calculator();

    while (true) {
      int firstNum, secondNum;
      char operator;

      // 입력을 받는 부분
      // 타입 불일치시 에러 메시지 출력
      try {
        System.out.print("첫 번째 숫자를 입력하세요: ");
        firstNum = nextInt(sc);
        System.out.print("두 번째 숫자를 입력하세요: ");
        secondNum = nextInt(sc);
        System.out.print("사칙연산 기호를 입력하세요: ");
        operator = sc.nextLine().charAt(0);
      } catch (InputMismatchException e) {
        System.out.println("입력 형식이 잘못되었습니다.");
        sc.nextLine();
        continue;
      }

      // Calculate 객체의 calculate 매서드를 활용하도록 변경
      int result = cal.calculate(firstNum, secondNum, operator);
      System.out.println("결과 : " + result);

      // 계산 결과를 보여주고, 삭제할 건지 묻는 대화
      System.out.println("지난 계산 결과 : " + cal.getCalHistory());
      System.out.println("가장 오래된 기록을 삭제하시겠습니까? (Y/N)");
      if (sc.nextLine().equalsIgnoreCase("y")) {
        int removedElement = cal.removeResult();
        String deleteStr =
            removedElement == -10101010 ? "삭제할 수 없습니다" : "삭제된 데이터: " + removedElement;
        System.out.println(deleteStr);
        System.out.println("지난 계산 결과 : " + cal.getCalHistory());
      }

      // 기록을 초기화할 건지 묻는 대화
      System.out.print("기록을 초기화하시겠습니까? (Y/N)");
      if (sc.nextLine().equalsIgnoreCase("y")) {
        cal.setCalHistory(new LinkedList<>());
        System.out.println("지난 계산 결과 : " + cal.getCalHistory());
      }

      // 더 계산할 건지 묻는 대화
      System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
      if (sc.nextLine().equals("exit")) {
        break;
      }
    }
  }

  // nextInt를 개행문자 신경쓰지 않고 활용하도록 하는 매서드
  public static int nextInt(Scanner sc) {
    int input = sc.nextInt();
    sc.nextLine();
    return input;
  }
}
