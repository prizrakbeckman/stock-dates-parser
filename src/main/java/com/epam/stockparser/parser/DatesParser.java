package com.epam.stockparser.parser;

import java.time.LocalDate;

public class DatesParser implements CustomDateParser<LocalDate, String> {

    public static final String SEPARATOR = "/";

    public LocalDate formatDate(String field) {
        String[] val = field.split(SEPARATOR);
        if(val.length != 3)
            throw new InvalidDateException("Invalid date format");

        return LocalDate.of(parseDateVal(val[2]),
                parseDateVal(val[1]),
                parseDateVal(val[0]));
    }
    private int parseDateVal(String val) {
        return Integer.parseInt(val);
    }
}
