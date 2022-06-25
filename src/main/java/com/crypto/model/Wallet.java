package com.crypto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Wallet {
    @Id
	@GeneratedValue
    private Long id;
	private Long userId;
    private Long currencyId;
    private double balance;

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

    public Long getCurrencyId(){
        return this.currencyId;
    }

    public void setCurrencyId(Long currencyId){
        this.currencyId = currencyId;
    }

    public double getBalance(){
        return this.balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }
}