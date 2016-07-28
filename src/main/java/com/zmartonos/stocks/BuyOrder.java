package com.zmartonos.stocks;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by zootanka on 07.11.15.
 */
public class BuyOrder {
    private final String isin;
    private final String currency;
    private final int amount;
    private final double unitPrice;
    private final LocalDate date;

    public BuyOrder(String isin, String currency, int amount, double unitPrice, LocalDate date) {
        this.isin = isin;
        this.currency = currency;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.date = date;
    }

    public String getIsin() {
        return isin;
    }

    public String getCurrency() {
        return currency;
    }

    public int getAmount() {
        return amount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public LocalDate getDate() {
        return date;
    }
}
