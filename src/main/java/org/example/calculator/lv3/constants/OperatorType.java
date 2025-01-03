package org.example.calculator.lv3.constants;

public enum OperatorType {
  // enum의 각 요소는 abstract method를 override함
  // 계산은 double로 수행, converter 구현체를 통해 T로 수렴되도록 함

  PLUS {
    @Override
    public double calculate(Number a, Number b) {
      return a.doubleValue() + b.doubleValue();
    }
  },
  MINUS {
    @Override
    public double calculate(Number a, Number b) {
      return a.doubleValue() - b.doubleValue();
    }
  },
  MULTIPLY {
    @Override
    public double calculate(Number a, Number b) {
      return a.doubleValue() * b.doubleValue();
    }
  },
  DIVIDE {
    @Override
    public double calculate(Number a, Number b) throws ArithmeticException {
      if (b.doubleValue() == 0.0) {
        throw new ArithmeticException("/ by zero");
      }
      return a.doubleValue() / b.doubleValue();
    }
  };

  // enum에 abstract method를 두어 enum의 각 요소들이 override하도록 함
  // Number로 parameter의 타입을 추상화함.
  public abstract double calculate(Number a, Number b);
}
