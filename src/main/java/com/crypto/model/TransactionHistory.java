package com.crypto.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION_HISTORY")
public class TransactionHistory {
	@Id
	@GeneratedValue
	private Long id;
    private Long userId;
    private Date transDate;
    private String transType;
    private Long currencyId;
    private double amount; 

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getUserId(){
        return this.userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Date getTransDate(){
        return this.transDate;
    }

    public void setTransDate(Date transDate){
        this.transDate = transDate;
    }

    public String getTransType(){
        return this.transType;
    }

    public void setTransType(String transType){
        this.transType = transType;
    }

    public Long getCurrencyId(){
        return this.currencyId;
    }

    public void setCurrencyId(Long currencyId){
        this.currencyId = currencyId;
    }

    public double getAmount(){
        return this.amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }
}