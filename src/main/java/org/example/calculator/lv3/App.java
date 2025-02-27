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

      // 입력을 받는 부분
      // 타입 불일치시 에러 메시지 출력
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

      // Calculate 객체의 calculate 매서드를 활용하여 계산
      // Calculate class가 Generic class로 변경되어 int, double 모두 지원
      try {
        int intResult = intCal.calculate((int) firstNum, (int) secondNum, operator);
        double doubleResult = doubleCal.calculate(firstNum, secondNum, operator);
        System.out.println("정수 계산 결과 : " + intResult);
        System.out.println("실수 계산 결과 : " + doubleResult);

        System.out.println("지난 정수 계산 결과 : " + intCal.getCalHistory());
        System.out.println("지난 실수 계산 결과 : " + doubleCal.getCalHistory());
      } catch (ArithmeticException e) {
        System.out.println("잘못된 계산식입니다 : " + e.getMessage());
        continue;
      }

      // 삭제할 건지 묻는 대화
      // 기록이 비어 있는 경우, "삭제할 수 없습니다." 메시지 출력
      try {
        System.out.println("가장 오래된 기록을 삭제하시겠습니까? (Y/N)");
        if (sc.nextLine().equalsIgnoreCase("y")) {
          int removedInt = intCal.removeResult();
          double removedDouble = doubleCal.removeResult();
          System.out.println("삭제된 정수 기록 : " + removedInt);
          System.out.println("삭제된 실수 기록 : " + removedDouble);

          System.out.println("지난 계산 결과(정수) : " + intCal.getCalHistory());
          System.out.println("지난 계산 결과(실수) : " + doubleCal.getCalHistory());
        }
      } catch (NoSuchElementException e) {
        System.out.println("삭제할 수 없습니다.");
        sc.nextLine();
      }

      // 필터링 된 계산 결과를 보여주는 부분
      // 필터링 기준이 되는 수 N을 입력 받아 수행.
      try {
        System.out.print("N보다 큰 계산 결과들을 보여드립니다. N을 입력하세요 : ");
        double input = nextFloat(sc);
        System.out.println("입력하신 수보다 큰 결과(정수) : " + intCal.getHistoryLessThan(input));
        System.out.println("입력하신 수보다 큰 결과(실수) : " + doubleCal.getHistoryLessThan(input));
      } catch (InputMismatchException e) {
        System.out.println("입력 형식이 잘못되었습니다.");
        sc.nextLine();
        continue;
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
