package com.epam.stockparser.validator;

import com.epam.stockparser.holidaysprovider.MarketHolidaysProvider;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;

public class MarketHolidaysValidator implements StockDateValidator<LocalDate> {

    private MarketHolidaysProvider marketHolidaysProvider = new MarketHolidaysProvider();

    public boolean isValid(LocalDate date){
        List<LocalDate> officialMarketHolidays = marketHolidaysProvider.retrieveMarketHolidays();
        return  officialMarketHolidays.contains(date) ||
                !isWeekend(date);
    }

    public static boolean isWeekend(final LocalDate ld)
    {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }

}
