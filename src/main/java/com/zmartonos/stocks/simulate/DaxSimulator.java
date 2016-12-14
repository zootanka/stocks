package com.zmartonos.stocks.simulate;

import com.zmartonos.stocks.DaxYearlyPriceCSVReader;
import com.zmartonos.stocks.Price;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by zmartonos on 14.12.16.
 */
public class DaxSimulator {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(final String[] argv) throws IOException, URISyntaxException {
        LOGGER.info("Starting DailyPivotSimulator...");

        // read yearly prices
        final List<Price> prices = DaxYearlyPriceCSVReader.readYearlyPriceCSV(
                DailyPivotSimulator.class.getClassLoader().getResource("prices/dax.csv").getFile());
    }
}
