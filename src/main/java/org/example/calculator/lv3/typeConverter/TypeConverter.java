package org.example.calculator.lv3.typeConverter;

// 다형성을 활용하여 Int, Double converter를 추상화하기 위한 interface
public interface TypeConverter<T> {

  T convert(double value);
}
