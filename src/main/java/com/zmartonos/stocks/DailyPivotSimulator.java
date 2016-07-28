package com.zmartonos.stocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by zootanka on 28.07.16.
 */
public class DailyPivotSimulator {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(final String[] argv) throws IOException, URISyntaxException {
        LOGGER.info("Starting DailyPivotSimulator...");

        final List<DailyPrice> dailyPrices = DailyPriceCSVReader.readDailyPriceCSV(
                DailyPivotSimulator.class.getClassLoader().getResource("prices/henkel.csv").getFile());

        dailyPrices.
                stream().
                parallel().
                forEach(
                        price -> LOGGER.info(
                                String.format("Daily pivot points for: %s is: %s",
                                        price, DailyPivotCalculator.calculateDailyPivot(price))));
    }
}
