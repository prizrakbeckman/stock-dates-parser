package com.epam.stockparser.parser;

import com.epam.stockparser.model.Stock;
import com.epam.stockparser.parser.CustomFileParser;
import com.epam.stockparser.parser.StockRecordsParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class StockFileParserTest {

    private static final String PATh = "ddddssdqfdsfd.csv";
    private static final String VALID_PATH = "stocks.csv";

    private CustomFileParser<Stock> fileParser;

    @BeforeClass
    public void setUp(){
    }

    @Test
    public void shouldThrowExceptionWhenStockParserHasAnInvalidFilePath(){
        fileParser = new StockRecordsParser(PATh);
        Assert.assertThrows(RuntimeException.class, () -> fileParser.parseFile());
    }
    @Test
    public void shouldReturnValidDataInListWhenStockParserHasAValidFilePath() throws IOException {
        fileParser = new StockRecordsParser(VALID_PATH);
        Assert.assertTrue(fileParser.parseFile().size()>0);
    }
}
