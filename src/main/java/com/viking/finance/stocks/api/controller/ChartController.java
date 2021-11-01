package com.viking.finance.stocks.api.controller;

import com.viking.finance.stocks.api.model.Price;
import com.viking.finance.stocks.api.model.yahoo.Chart;
import com.viking.finance.stocks.api.service.ChartService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChartController {

    private final ChartService priceService;

    public ChartController(ChartService priceService) {
        this.priceService = priceService;
    }

    @GetMapping(value = "/v1/chart/symbol/{symbol}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Chart stockValue(@PathVariable String symbol) {
        return priceService.getValueForSymbol(symbol);
    }

    @GetMapping(value = "/v1/chart/all/username/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Price> allValues(@PathVariable String username) {
        return priceService.getFollowedPrices(username);
    }


}
