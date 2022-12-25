package com.epam.stockparser.model;

public class Stock {

    private String stockDate;

    public Stock setStockDate(String stockDate) {
        this.stockDate = stockDate;
        return this;
    }

    public String getStockDate() {
        return stockDate;
    }
}