package com.viking.finance.stocks.api.adapter;

import com.viking.finance.stocks.api.model.yahoo.YahooResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

@Component
public class YahooFinanceAdapter {

    private final RestTemplate restTemplate;

    public YahooFinanceAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<YahooResponse> retrieveChartInfo(String symbol) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-API-KEY","");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<YahooResponse> response =
                restTemplate.exchange("https://yfapi.net/v8/finance/chart/" + symbol
                        , HttpMethod.GET
                        , entity
                        , YahooResponse.class);

        return Optional.ofNullable(response.getBody());
    }
}
