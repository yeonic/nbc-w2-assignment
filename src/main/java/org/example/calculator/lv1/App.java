package org.example.calculator.lv1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (true) {
      int firstNum, secondNum;
      char operator;
      try {
        System.out.print("첫 번째 숫자를 입력하세요: ");
        firstNum = nextInt(sc);
        System.out.print("두 번째 숫자를 입력하세요: ");
        secondNum = nextInt(sc);
        System.out.print("사칙연산 기호를 입력하세요: ");
        operator = sc.nextLine().charAt(0);
      } catch (InputMismatchException e) {
        System.out.println("입력 형식이 잘못되었습니다.");
        return;
      }

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
      // -10101010은 에러가 생긴 경우
      System.out.println("결과: " + result);

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
