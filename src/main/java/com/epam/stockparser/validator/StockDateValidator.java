package com.epam.stockparser.validator;

public interface StockDateValidator<T> {

    boolean isValid(T t);

}
