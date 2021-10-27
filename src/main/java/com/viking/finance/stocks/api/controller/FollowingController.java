package com.viking.finance.stocks.api.controller;

import com.viking.finance.stocks.api.model.Follow;
import com.viking.finance.stocks.api.service.FollowingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowingController {

    private final FollowingService followingService;

    public FollowingController(FollowingService followingService) {
        this.followingService = followingService;
    }

    @PostMapping(value = "/v1/follow")
    public void followSymbol(@RequestBody Follow follow) {
        followingService.insertNewFollowedStock(follow);
    }
}
