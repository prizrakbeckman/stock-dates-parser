package com.epam.stockparser.processor;

import com.epam.stockparser.model.Stock;
import com.epam.stockparser.parser.CustomDateParser;
import com.epam.stockparser.parser.CustomFileParser;
import com.epam.stockparser.parser.DatesParser;
import com.epam.stockparser.parser.StockRecordsParser;
import com.epam.stockparser.processor.StockProcessor;
import com.epam.stockparser.validator.MarketHolidaysValidator;
import com.epam.stockparser.validator.StockDateValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StockProcessorTest {

    private static final String PATh = "stocks.csv";
    private static final String[] DATES_HOLIDAYS = {"04/07/2022","07/07/2022", "08/07/2022"};
    private List<LocalDate> stockHolidays;

    private StockDateValidator<LocalDate> validator;
    private CustomFileParser<Stock> parser;
    private CustomDateParser<LocalDate, String> dateParser;

    private StockProcessor processor ;

    @BeforeClass
    public void setUp(){
        validator = new MarketHolidaysValidator();
        parser = new StockRecordsParser(PATh);
        dateParser = new DatesParser();
        processor =new StockProcessor(validator, parser, dateParser);

        stockHolidays = buildStockHolidaysExpected();
    }



    @Test
    public void shouldReturnNotEmptyHolidayDatesListWhenFileIsValidAndHasCorrectData() {

        List<LocalDate> dateList = processor.findMarketHolidays();
        Assert.assertTrue(dateList.size() == 3);
        Assert.assertTrue(dateList.equals(stockHolidays));
    }

    private List<LocalDate> buildStockHolidaysExpected() {
        List<LocalDate> dates = new ArrayList<>();
        Arrays.stream(DATES_HOLIDAYS)
                        .forEach(val -> dates.add(dateParser.formatDate(val)));
        return dates;
    }
}
