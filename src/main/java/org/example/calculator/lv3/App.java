package org.example.calculator.lv3;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.example.calculator.lv3.typeConverter.DoubleConverter;
import org.example.calculator.lv3.typeConverter.IntConverter;

public class App {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ArithmeticCalculator<Integer> intCal = new ArithmeticCalculator<>(new IntConverter());
    ArithmeticCalculator<Double> doubleCal = new ArithmeticCalculator<>(new DoubleConverter());

    while (true) {
      double firstNum, secondNum;
      char operator;

      try {
        System.out.print("첫 번째 수를 입력하세요: ");
        firstNum = nextFloat(sc);
        System.out.print("두 번째 수를 입력하세요: ");
        secondNum = nextFloat(sc);
        System.out.print("사칙연산 기호를 입력하세요: ");
        operator = sc.nextLine().charAt(0);
      } catch (InputMismatchException e) {
        System.out.println("입력 형식이 잘못되었습니다.");
        sc.nextLine();
        continue;
      }

      // Calculate 객체의 calculate 매서드를 활용하도록 변경
      int intResult = intCal.calculate((int) firstNum, (int) secondNum, operator);
      double doubleResult = doubleCal.calculate(firstNum, secondNum, operator);
      System.out.println("정수 계산 결과 : " + intResult);
      System.out.println("실수 계산 결과 : " + doubleResult);

      // 계산 결과를 보여주고, 삭제할 건지 묻는 대화
      System.out.println("지난 정수 계산 결과 : " + intCal.getCalHistory());
      System.out.println("지난 실수 계산 결과 : " + doubleCal.getCalHistory());

      try {
        System.out.println("가장 오래된 기록을 삭제하시겠습니까? (Y/N)");
        if (sc.nextLine().equalsIgnoreCase("y")) {
          int removedInt = intCal.removeResult();
          double removedDouble = doubleCal.removeResult();
          System.out.println("삭제된 정수 기록 : " + removedInt);
          System.out.println("삭제된 실수 기록 : " + removedDouble);

          System.out.println("지난 정수 계산 결과 : " + intCal.getCalHistory());
          System.out.println("지난 실수 계산 결과 : " + doubleCal.getCalHistory());
        }
      } catch (NoSuchElementException e) {
        System.out.println("삭제할 수 없습니다.");
        sc.nextLine();
      }

      System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
      if (sc.nextLine().equals("exit")) {
        break;
      }
    }
  }

  // nextFloat를 개행문자 신경쓰지 않고 활용하도록 하는 매서드
  public static double nextFloat(Scanner sc) {
    double input = sc.nextFloat();
    sc.nextLine();
    return input;
  }
}
