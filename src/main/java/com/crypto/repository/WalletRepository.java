package com.crypto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.crypto.model.Wallet;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
    List<Wallet> findByUserId(long userId);
}