package com.viking.finance.stocks.api.model;

import javax.persistence.*;

@Table(name = "FollowedStock")
@Entity
public class FollowData {

    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column
    private String username;

    @Column
    private String symbol;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
