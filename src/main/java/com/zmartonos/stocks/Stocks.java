package com.zmartonos.stocks;

import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by zootanka on 07.11.15.
 */
public class Stocks {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(final String[] argv) throws IOException {
        LOGGER.info("Starting app...");

        final List<BuyOrder> buyOrders = Lists.newArrayList();
        buyOrders.add(new BuyOrder("DE0005552004", "EUR", 43, 27.87, LocalDate.parse("2015-02-11", Formats.yyyyMMddFormatter)));
        buyOrders.add(new BuyOrder("DE0005552004", "EUR", 33, 28.82, LocalDate.parse("2015-03-13", Formats.yyyyMMddFormatter)));
        buyOrders.add(new BuyOrder("DE0005552004", "EUR", 50, 27.65, LocalDate.parse("2015-05-13", Formats.yyyyMMddFormatter)));
        buyOrders.add(new BuyOrder("DE0005552004", "EUR", 45, 27.35, LocalDate.parse("2015-06-01", Formats.yyyyMMddFormatter)));

        final List<Price> prices = DailyPriceCSVReader.readDailyPriceCSV("src/test/resources/dt-wohnen.csv");

        prices.forEach(e->LOGGER.info(String.format("Date: %s\t Vola: %f\t Change: %f\t Open: %f\t Close: %f",e
                .getDate(),(e
                .getHigh()-e
                .getLow()),(e.getClose() - e.getOpen()), e.getOpen(), e.getClose())));
    }
}
