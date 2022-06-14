package com.example.springtrader.common.domain.entity;

import com.example.springtrader.common.enums.CurrencyType;
import com.example.springtrader.common.enums.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table
@Getter
@NoArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Long id;

    @OneToMany
    private List<CoinWallet> coinWalletList = new ArrayList<>();

    @OneToMany
    private List<TradeHistory> tradeHistoryList = new ArrayList<>();

    private Double money;

    public void updateCoinWallet(CurrencyType currencyType, Double avgPrice) {

        coinWalletList.stream()
                .filter(wallet -> wallet.getCurrencyType().equals(currencyType))
                .findFirst()
                .ifPresentOrElse(wallet -> wallet.update(avgPrice), () -> coinWalletList.add(new CoinWallet(currencyType,0.0, 0.0)));
    }

    public void addHistory(TradeHistory tradeHistory) {
        TradeType tradeType = tradeHistory.getTradeType();
        switch (tradeType) {
            case ASK: this.money += tradeHistory.getTotalPrice();
            case BID: this.money -= tradeHistory.getTotalPrice();
        }
        this.tradeHistoryList.add(tradeHistory);
    }

    public Account(Double money) {
        this.money = money;
    }

    public boolean isBid(CurrencyType currencyType) {
        return getBalance(currencyType) == 0 && money > 0;
    }

    public boolean isAsk(CurrencyType currencyType) {
        return getBalance(currencyType) > 0;
    }

    public CoinWallet getCoinWallet(CurrencyType currencyType) {
        return coinWalletList.stream()
                .filter(coinWallet -> coinWallet.getCurrencyType().equals(currencyType))
                .findFirst()
                .orElseGet(() -> new CoinWallet(currencyType, 0.0, 0.0));
    }

    private Double getBalance(CurrencyType currencyType) {

        return coinWalletList.stream()
                .filter(coinWallet -> coinWallet.getCurrencyType().equals(currencyType))
                .findFirst()
                .orElseGet(() -> new CoinWallet(currencyType, 0.0, 0.0))
                .getBalance();
    }

}
