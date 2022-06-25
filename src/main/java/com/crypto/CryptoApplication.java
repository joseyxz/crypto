package com.crypto;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.crypto.constant.CommonConstant;
import com.crypto.model.Currency;
import com.crypto.service.CryptoService;

@SpringBootApplication
@EnableScheduling
public class CryptoApplication {
	private static final Logger log = LoggerFactory.getLogger(CryptoApplication.class);

	@Autowired
	CryptoService service;
	
	public static void main(String[] args) {
		SpringApplication.run(CryptoApplication.class, args);
	}

	/**
	 * 1. Create a 10 seconds interval scheduler to retrieve the pricing from the source above and store the best pricing into the database.
	 */
	@Scheduled(fixedRate = 10000)
	public void getLatestPrices() {
		log.info("Retrieving latest prices...");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Currency>> responseEntity = 
		restTemplate.exchange(
			CommonConstant.API_URL,
			HttpMethod.GET,
			null,
			new ParameterizedTypeReference<List<Currency>>() {}
		);
		List<Currency> currencyList = responseEntity.getBody();

		for (String cur : CommonConstant.sysCurList) {
			for (Currency currency : currencyList) {
				if (cur.equalsIgnoreCase(currency.getSymbol())){
					service.updateLatestPrices(currency);
				}
			}
		}
		log.info("Completed retrieval of latest prices.");
	}
}
