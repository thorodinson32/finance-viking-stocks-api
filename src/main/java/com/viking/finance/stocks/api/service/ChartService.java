package com.viking.finance.stocks.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viking.finance.stocks.api.adapter.YahooFinanceAdapter;
import com.viking.finance.stocks.api.model.Follow;
import com.viking.finance.stocks.api.model.Price;
import com.viking.finance.stocks.api.model.yahoo.Chart;
import com.viking.finance.stocks.api.model.yahoo.YahooResponse;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ChartService {

    private final YahooFinanceAdapter yahooFinanceAdapter;
    private final FollowingService followingService;

    public ChartService(YahooFinanceAdapter yahooFinanceAdapter,
                        FollowingService followingService) {
        this.yahooFinanceAdapter = yahooFinanceAdapter;
        this.followingService = followingService;
    }

    public Chart getValueForSymbol(String symbol) {
        YahooResponse response = yahooFinanceAdapter.retrieveChartInfo(symbol);

        return response.getChart();
    }

    public List<Price> getFollowedPrices(String username) {
        List<Follow> follows = followingService.retrieveAllFollows(username);

        List<CompletableFuture<Chart>> responses = follows.stream()
                .map(Follow::getSymbol)
                .map(symbol -> CompletableFuture.
                        supplyAsync(() -> getValueForSymbol(symbol)))
                .collect(Collectors.toList());

        return responses.stream()
                .map(CompletableFuture::join)
                .map(chart -> {
                    Price price = new Price();
                    price.setStock(chart.getResult()
                            .get(0)
                            .getMeta()
                            .getSymbol());
                    price.setPrice(chart.getResult()
                            .get(0)
                            .getMeta()
                            .getRegularMarketPrice());

                    return price;
                })
                .collect(Collectors.toList());
    }

}
