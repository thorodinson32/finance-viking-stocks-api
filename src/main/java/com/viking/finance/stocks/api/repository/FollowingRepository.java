package com.viking.finance.stocks.api.repository;

import com.viking.finance.stocks.api.model.FollowData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowingRepository extends CrudRepository<FollowData, Integer> {

    List<FollowData> findAllByUsername(String username);
}