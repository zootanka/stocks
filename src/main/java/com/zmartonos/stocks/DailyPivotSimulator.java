package com.zmartonos.stocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javatuples.Pair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by zootanka on 28.07.16.
 */
public class DailyPivotSimulator {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(final String[] argv) throws IOException, URISyntaxException {
        LOGGER.info("Starting DailyPivotSimulator...");

        // read daily prices
        final List<DailyPrice> dailyPrices = DailyPriceCSVReader.readDailyPriceCSV(
                DailyPivotSimulator.class.getClassLoader().getResource("prices/henkel.csv").getFile());

        // create pair of two consecutive trading day pricees
        final List<Pair<DailyPrice, DailyPrice>> twoDayPrices =
                IntStream.
                        range(0, dailyPrices.size() -1).
                        mapToObj(i -> Pair.with(dailyPrices.get(i), dailyPrices.get(i+1))).
                        collect(Collectors.toList());

        // calculate pivot points
        final List<DailyPivot> dailyPivots = twoDayPrices.
                stream().
                parallel().
                map(pair -> DailyPivotCalculator.calculateDailyPivot(pair.getValue0(),pair.getValue1())).
                collect(Collectors.toList());

        AtomicInteger rising = new AtomicInteger(0);
        AtomicInteger falling = new AtomicInteger(0);

        dailyPivots.stream().parallel().forEach(pivot->{
            DailyPrice dailyPrice = pivot.getDayPrice();
            if (dailyPrice.getClose() > dailyPrice.getOpen()){
                rising.incrementAndGet();
            } else {
                falling.incrementAndGet();
            }
        });
        LOGGER.info("Rising: "+rising+" falling: "+falling);
    }
}
