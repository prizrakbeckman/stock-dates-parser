package com.epam.stockparser.parser;

import com.epam.stockparser.model.Stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class StockRecordsParser implements CustomFileParser<Stock> {

    private static final String STOCK_SEPARATOR = ",";
    private String file;

    public StockRecordsParser(String path) {
        this.file = path;
    }

    public List<Stock> parseFile() throws IOException {
        List<Stock> stocks = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(file);
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(streamReader)) {
            br.readLine(); // this will read the first line to avoid the header
            String line;
            while ((line = br.readLine()) != null) {
                stocks.add(processLine(line));
            }
        }
        return stocks;
    }

    private Stock processLine(String line) {
        return new Stock()
                .setStockDate(line.substring(0, line.indexOf(STOCK_SEPARATOR)));
    }

}
