package com.viking.finance.stocks.api.model.yahoo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {

    private String symbol;
    private double regularMarketPrice;
    private double chartPreviousClose;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getRegularMarketPrice() {
        return regularMarketPrice;
    }

    public void setRegularMarketPrice(double regularMarketPrice) {
        this.regularMarketPrice = regularMarketPrice;
    }

    public double getChartPreviousClose() {
        return chartPreviousClose;
    }

    public void setChartPreviousClose(double chartPreviousClose) {
        this.chartPreviousClose = chartPreviousClose;
    }
}