package com.crypto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String symbol;
    private double bidPrice;
    private double askPrice;

    public Currency(){

    }
    
    public Currency(String symbol, double bidPrice, double askPrice){
        this.symbol = symbol;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getSymbol(){
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getBidPrice(){
        return this.bidPrice;
    }

    public void setBidPrice(double bidPrice){
        this.bidPrice = bidPrice;
    }

    public double getAskPrice(){
        return this.askPrice;
    }

    public void setAskPrice(double askPrice){
        this.askPrice = askPrice;
    }
}