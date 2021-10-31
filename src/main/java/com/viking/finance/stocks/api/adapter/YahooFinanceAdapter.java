package com.viking.finance.stocks.api.adapter;

import com.viking.finance.stocks.api.model.yahoo.YahooResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class YahooFinanceAdapter {

    private static final String API_KEY_HEADER = "X-API-KEY";
    private final RestTemplate restTemplate;
    private final String yahooApiUrl;
    private final String yahooApiKey;

    public YahooFinanceAdapter(RestTemplate restTemplate,
                               @Value("${yahoo.api.url}") String yahooApiUrl,
                               @Value("${yahoo.api.key}") String yahooApiKey) {
        this.restTemplate = restTemplate;
        this.yahooApiUrl = yahooApiUrl;
        this.yahooApiKey = yahooApiKey;
    }

    @Cacheable(cacheNames = "prices", unless = "#result==null")
    public YahooResponse retrieveChartInfo(String symbol) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(API_KEY_HEADER, yahooApiKey);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<YahooResponse> response =
                restTemplate.exchange(yahooApiUrl + symbol
                        , HttpMethod.GET
                        , entity
                        , YahooResponse.class);

        return response.getBody();
    }

    @Scheduled(fixedRate = 60000)
    @CacheEvict(value = "prices", allEntries=true)
    public void clearPricesCache() {

    }

}
