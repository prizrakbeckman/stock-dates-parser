package com.epam.stockparser.processor;

import com.epam.stockparser.model.Stock;
import com.epam.stockparser.parser.CustomDateParser;
import com.epam.stockparser.parser.CustomFileParser;
import com.epam.stockparser.validator.StockDateValidator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StockProcessor {

    private static final Logger LOG = Logger.getLogger(StockProcessor.class.getName());

    private StockDateValidator<LocalDate> validator;
    private CustomFileParser<Stock> parser;
    private CustomDateParser<LocalDate, String> datesParser;

    public StockProcessor(StockDateValidator<LocalDate> validator,
                          CustomFileParser<Stock> parser,
                          CustomDateParser<LocalDate, String> datesParser) {
        this.validator = validator;
        this.parser = parser;
        this.datesParser = datesParser;
    }

    public List<LocalDate> findMarketHolidays() {
        List<LocalDate> dates = new LinkedList<>();
        try {
            List<LocalDate> stockDates = getStockDates();
            LocalDate startDate = stockDates.get(0);
            LocalDate endDate = stockDates.get(stockDates.size() - 1);

            for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                if (!stockDates.contains(date) && validator.isValid(date)) {
                    dates.add(date);
                }
            }

        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return dates;
    }

    private List<LocalDate > getStockDates() throws IOException {
        return parser.parseFile()
                .stream()
                .map(val -> datesParser.formatDate(val.getStockDate()))
                .collect(Collectors.toList());
    }


}
