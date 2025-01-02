package org.example.calculator.lv3.constants;

public enum OperatorType {
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

  public abstract double calculate(Number a, Number b);
}
