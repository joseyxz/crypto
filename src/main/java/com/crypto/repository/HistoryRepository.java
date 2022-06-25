package com.crypto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crypto.model.TransactionHistory;

public interface HistoryRepository extends CrudRepository<TransactionHistory, Long> {
    List<TransactionHistory> findByUserIdOrderByTransDateAsc(long userId);
}