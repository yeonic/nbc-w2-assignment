package org.example.calculator.lv3.typeConverter;

public class DoubleConverter implements TypeConverter<Double> {

  // double인 경우, 소숫점 아래 2자리 수까지 표현
  @Override
  public Double convert(double value) {
    return Math.round(value * 100.0) / 100.0;
  }
}
