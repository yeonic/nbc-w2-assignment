package org.example.calculator.lv3.typeConverter;

public class IntConverter implements TypeConverter<Integer> {

  @Override
  public Integer convert(double value) {
    return (int) value;
  }
}
