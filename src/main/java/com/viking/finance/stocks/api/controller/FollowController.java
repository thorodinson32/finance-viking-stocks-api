package com.viking.finance.stocks.api.controller;

import com.viking.finance.stocks.api.model.Follow;
import com.viking.finance.stocks.api.service.FollowingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FollowController {

    private final FollowingService followingService;

    public FollowController(FollowingService followingService) {
        this.followingService = followingService;
    }

    @PostMapping("/v1/follow")
    public void followSymbol(@RequestBody Follow follow) {
        followingService.insertNewFollowedStock(follow);
    }

    @GetMapping("v1/follow/{username}")
    public List<Follow> allFollowingSymbols(@PathVariable String username) {
        return followingService.retrieveAllFollows(username);
    }

}
