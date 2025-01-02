package org.example.calculator.lv3.typeConverter;

public class DoubleConverter implements TypeConverter<Double> {

  @Override
  public Double convert(double value) {
    return Math.round(value * 100.0) / 100.0;
  }
}
