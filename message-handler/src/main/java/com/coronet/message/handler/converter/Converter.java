package com.coronet.message.handler.converter;

public interface Converter<T, F> {

    public T convert(F object);
}
