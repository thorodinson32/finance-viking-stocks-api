package com.viking.finance.stocks.api.model;

import javax.persistence.*;

@Table(name = "follows")
@Entity
public class FollowData {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followId;

    @Column
    private String username;

    @Column
    private String symbol;


    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int id) {
        this.followId = followId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
