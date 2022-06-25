package com.crypto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {
	@Id
	@GeneratedValue
	private Long id;
    private String symbol;
    private double bidPrice;
    private double sellPrice;

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

    public double getSellPrice(){
        return this.sellPrice;
    }

    public void setSellPrice(double sellPrice){
        this.sellPrice = sellPrice;
    }
}