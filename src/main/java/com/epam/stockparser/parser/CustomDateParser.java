package com.epam.stockparser.parser;

public interface CustomDateParser<T,U> {
    T formatDate(U field);
}
