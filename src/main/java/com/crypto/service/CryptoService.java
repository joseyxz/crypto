package com.crypto.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.crypto.repository.UserInfoRepository;
import com.crypto.repository.HistoryRepository;
import com.crypto.repository.WalletRepository;
import com.crypto.constant.CommonConstant;
import com.crypto.model.Currency;
import com.crypto.model.Transaction;
import com.crypto.model.TransactionHistory;
import com.crypto.model.Wallet;
import com.crypto.repository.CurrencyRepository;

@Service
public class CryptoService {
	private static final Logger log = LoggerFactory.getLogger(CryptoService.class);

	@Autowired
	UserInfoRepository userRepository;
	@Autowired
	HistoryRepository historyRepository;
	@Autowired
	WalletRepository walletRepository;
	@Autowired
	CurrencyRepository currencyRepository;

    public void updateLatestPrices(Currency cur){
        Optional<Currency> currency = currencyRepository.findBySymbolIgnoreCase(cur.getSymbol());
        if (currency.isPresent()) {
            Currency existingCur = currency.get();
            existingCur.setBidPrice(cur.getBidPrice());
            existingCur.setAskPrice(cur.getAskPrice());
            currencyRepository.save(existingCur);
        } else {
            currencyRepository.save(new Currency(cur.getSymbol(), cur.getBidPrice(), cur.getAskPrice()));
        }
    }

    public Currency getPriceBySymbol(String symbol){
        Optional<Currency> currency = currencyRepository.findBySymbolIgnoreCase(symbol);
        if (currency.isPresent()) {
			return currency.get();
		}
        return null;
    }

    public List<Currency> getAllPrice(){
        List<Currency> priceList = new ArrayList<Currency>();
		currencyRepository.findAll().forEach(priceList::add);
        return priceList;
    }

    public HttpStatus submitTrade(Transaction transaction) {
		try {
            if (!CommonConstant.sysCurList.contains(transaction.getSymbol())){
                return HttpStatus.BAD_REQUEST;
            }
			Optional<Currency> currency = currencyRepository.findBySymbolIgnoreCase(transaction.getSymbol());
			if (currency.isPresent()) {
				Currency currencyInfo = currency.get();
				
				Optional<Wallet> walletToAdd = null;
				Optional<Wallet> walletToDeduct = null;
				double addAmount = 0;

				if (transaction.getTransType().equalsIgnoreCase(CommonConstant.TYPE_BUY)) {
					addAmount = transaction.getAmount() / currencyInfo.getAskPrice();
					walletToAdd = walletRepository.findByUserIdAndCurrencyId(transaction.getUserId(), currencyInfo.getId());
					walletToDeduct = walletRepository.findByUserIdAndCurrencyId(transaction.getUserId(), CommonConstant.CUR_USDT);
				} else if (transaction.getTransType().equalsIgnoreCase(CommonConstant.TYPE_SELL)) {
					addAmount = transaction.getAmount() * currencyInfo.getBidPrice();
					walletToAdd = walletRepository.findByUserIdAndCurrencyId(transaction.getUserId(), CommonConstant.CUR_USDT);
					walletToDeduct = walletRepository.findByUserIdAndCurrencyId(transaction.getUserId(), currencyInfo.getId());
				} else {
                    return HttpStatus.BAD_REQUEST;
                }

				// Perform wallet deduction
				if (walletToDeduct.isPresent()) {
					Wallet walletInfo = walletToDeduct.get();
					double balance = walletInfo.getBalance() - transaction.getAmount();
					if (balance < 0)
						return HttpStatus.BAD_REQUEST;
					walletInfo.setBalance(balance);
					walletRepository.save(walletInfo);
				} else {
					return HttpStatus.BAD_REQUEST;
				}

				// Perform wallet addition
				if (walletToAdd.isPresent()) {
					Wallet walletInfo = walletToAdd.get();
					double balance = walletInfo.getBalance() + addAmount;
					walletInfo.setBalance(balance);
					walletRepository.save(walletInfo);
				} else {
					walletRepository.save(new Wallet(transaction.getUserId(), currencyInfo.getId(), addAmount));
				}
				
				// Add transaction history
				historyRepository.save(new TransactionHistory(transaction.getUserId(), new Date(), transaction.getTransType(), currencyInfo.getId(), transaction.getAmount()));
			} else {
                return HttpStatus.BAD_REQUEST;
            }
		} catch (Exception e) {
			log.error("TRADE_EXCEPTION", e);
            return HttpStatus.INTERNAL_SERVER_ERROR;
		}
        return HttpStatus.CREATED;
	}

    public List<Wallet> getWalletBalanceByUserId(long id) {
		List<Wallet> balanceList = new ArrayList<Wallet>();
		walletRepository.findByUserId(id).forEach(balanceList::add);
		return balanceList;
	}

    public List<TransactionHistory> getUserTransactionHistory(long id) {
		List<TransactionHistory> history = new ArrayList<TransactionHistory>();
		historyRepository.findByUserIdOrderByTransDateAsc(id).forEach(history::add);
		return history;
	}
}
