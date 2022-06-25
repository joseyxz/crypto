package com.crypto.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.crypto.model.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    Optional<Currency> findBySymbolIgnoreCase(String symbol);
}
