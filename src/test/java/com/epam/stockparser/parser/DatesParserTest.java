package com.epam.stockparser.parser;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Objects;

public class DatesParserTest {

    private CustomDateParser<LocalDate, String> dateParser;
    private static final String INVALID_DATE = "01-02/2022";
    private static final String VALID_DATE = "01/02/2022";



    @BeforeClass
    public void setUp(){
        dateParser = new DatesParser();
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenDateHasNotValidFormat(){
        Assert.assertThrows(RuntimeException.class, () -> dateParser.formatDate((INVALID_DATE)));
    }

    @Test
    public void shouldReturnLocalDateWhenDateIsValid(){
        Assert.assertTrue(Objects.nonNull(dateParser.formatDate(VALID_DATE)));
    }
}
