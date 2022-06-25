package com.crypto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.model.Currency;
import com.crypto.model.Transaction;
import com.crypto.model.TransactionHistory;
import com.crypto.model.Wallet;
import com.crypto.service.CryptoService;

@RestController
@RequestMapping("/api")
public class CryptoController {
	@Autowired
	CryptoService service;

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
		Currency currency = service.getPriceBySymbol(symbol);
		if (currency != null) {
			return new ResponseEntity<>(currency, HttpStatus.OK);
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
		List<Currency> priceList = service.getAllPrice();
		if (priceList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(priceList, HttpStatus.OK);
	}

	@PostMapping("/trade")
	public ResponseEntity<TransactionHistory> submitTrade(@RequestBody Transaction transaction) {
		HttpStatus result = service.submitTrade(transaction);
		return new ResponseEntity<>(null, result);
	}

	/**
	 * 4. Create an api to retrieve the user’s crypto currencies wallet balance
	 * @param id
	 * @return
	 */
	@GetMapping("/user/{id}/wallet")
	public ResponseEntity<List<Wallet>> getWalletBalanceByUserId(@PathVariable("id") long id) {
		List<Wallet> balanceList = service.getWalletBalanceByUserId(id);
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
		List<TransactionHistory> history = service.getUserTransactionHistory(id);
		if (history.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(history, HttpStatus.OK);
	}
}
