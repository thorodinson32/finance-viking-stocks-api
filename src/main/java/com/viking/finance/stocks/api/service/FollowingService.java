package com.viking.finance.stocks.api.service;

import com.viking.finance.stocks.api.model.Follow;
import com.viking.finance.stocks.api.model.FollowData;
import com.viking.finance.stocks.api.repository.FollowingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowingService {

    private final FollowingRepository followingRepository;

    public FollowingService(FollowingRepository followingRepository) {
        this.followingRepository = followingRepository;
    }

    public void insertNewFollowedStock(Follow follow) {
        FollowData followData = new FollowData();
        followData.setSymbol(follow.getSymbol());
        followData.setUsername(follow.getUsername());

        followingRepository.save(followData);
    }

    public List<Follow> retrieveAllFollows(String username) {
        Iterable<FollowData> result = followingRepository.findAllByUsername(username);

        List<Follow> allFollows = new ArrayList<>();

        result.forEach(data -> {
            Follow follow = new Follow();
            follow.setSymbol(data.getSymbol());
            follow.setUsername(data.getUsername());
            allFollows.add(follow);
        });

        return allFollows;
    }

}
