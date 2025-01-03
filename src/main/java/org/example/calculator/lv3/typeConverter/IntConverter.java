package org.example.calculator.lv3.typeConverter;

public class IntConverter implements TypeConverter<Integer> {

  // int인 경우, 소수점 아래는 버리고, 정수 부분만 남긴다.
  @Override
  public Integer convert(double value) {
    return (int) value;
  }
}
