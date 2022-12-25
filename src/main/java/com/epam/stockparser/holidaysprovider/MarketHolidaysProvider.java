package com.epam.stockparser.holidaysprovider;

import com.epam.stockparser.parser.DatesParser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MarketHolidaysProvider {

    private DatesParser datesParser = new DatesParser();

    private static String[] holidayMarkets = {"17/01/2022", "21/02/2022", "15/04/2022",
            "07/05/2022", "20/06/2022", "01/07/2022",
            "04/07/2022", "05/09/2022", "10/10/2022",
            "11/11/2022", "24/11/2022", "25/11/2022",
            "23/12/2022", "25/12/2022", "26/12/2022",
            "30/12/2022"};

    public List<LocalDate> retrieveMarketHolidays(){
        List<LocalDate> list = new ArrayList<>();
        for (String val : holidayMarkets) {
            LocalDate localDate = datesParser.formatDate(val);
            list.add(localDate);
        }
        return list;
    }

}
