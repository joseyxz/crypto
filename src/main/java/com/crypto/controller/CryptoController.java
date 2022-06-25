package com.crypto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.model.Currency;
import com.crypto.model.TransactionHistory;
import com.crypto.model.Wallet;
import com.crypto.repository.CurrencyRepository;
import com.crypto.repository.HistoryRepository;
import com.crypto.repository.WalletRepository;

@RestController
@RequestMapping("/api")
public class CryptoController {
	@Autowired
	HistoryRepository historyRepository;
	@Autowired
	WalletRepository walletRepository;
	@Autowired
	CurrencyRepository currencyRepository;

    @GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	/**
	 * 2. Create an api to retrieve the latest best aggregated price (by currency)
	 * @param symbol
	 * @return
	 */
	@GetMapping("/price/{symbol}")
	public ResponseEntity<Currency> getPriceBySymbol(@PathVariable("symbol") String symbol) {
		Optional<Currency> currency = currencyRepository.findBySymbolIgnoreCase(symbol);
		if (currency.isPresent()) {
			return new ResponseEntity<>(currency.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * 2. Create an api to retrieve the latest best aggregated price (all currencies)
	 * @param symbol
	 * @return
	 */
	@GetMapping("/price")
	public ResponseEntity<List<Currency>> getAllPrice() {
		List<Currency> priceList = new ArrayList<Currency>();
		currencyRepository.findAll().forEach(priceList::add);
		if (priceList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(priceList, HttpStatus.OK);
	}

	/**
	 * 4. Create an api to retrieve the user’s crypto currencies wallet balance
	 * @param id
	 * @return
	 */
	@GetMapping("/user/{id}/wallet")
	public ResponseEntity<List<Wallet>> getWalletBalanceByUserId(@PathVariable("id") long id) {
		List<Wallet> balanceList = new ArrayList<Wallet>();
		walletRepository.findByUserId(id).forEach(balanceList::add);
		if (balanceList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(balanceList, HttpStatus.OK);
	}

	/**
	 * 5. Create an api to retrieve the user trading history.
	 * @param id
	 * @return
	 */
	@GetMapping("/user/{id}/history")
	public ResponseEntity<List<TransactionHistory>> getUserTransactionHistory(@PathVariable("id") long id) {
		List<TransactionHistory> history = new ArrayList<TransactionHistory>();
		historyRepository.findByUserIdOrderByTransDateAsc(id).forEach(history::add);
		if (history.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(history, HttpStatus.OK);
	}
}
