package com.crypto.model;

public class Transaction {
    private Long userId;
    private String symbol;
    private String transType;
    private double amount; 

    public Long getUserId(){
        return this.userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public String getSymbol(){
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTransType(){
        return this.transType;
    }

    public void setTransType(String transType){
        this.transType = transType;
    }

    public double getAmount(){
        return this.amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }
}