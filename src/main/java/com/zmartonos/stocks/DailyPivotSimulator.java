package com.zmartonos.stocks;

import com.google.common.util.concurrent.AtomicDouble;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javatuples.Pair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
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

        LOGGER.info("Prices: "+dailyPrices.get(0).getClose()+" :: "+dailyPrices.get(dailyPrices.size()-1).getClose());
        // create pair of two consecutive trading day prices
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

        AtomicDouble purchase = new AtomicDouble(0);
        AtomicDouble profit = new AtomicDouble(0);

        dailyPivots.stream().parallel().forEach(pivot->{
            DailyPrice dailyPrice = pivot.getDayPrice();
            if (dailyPrice.getClose() > dailyPrice.getOpen()){
                rising.incrementAndGet();
            } else {
                falling.incrementAndGet();
            }

            if (pivot.getS1() > dailyPrice.getLow() && purchase.get() == 0.0){
                purchase.set(pivot.getS1());
            }

            if (dailyPrice.getHigh() > pivot.getR1() && purchase.get() != 0.0){
                profit.addAndGet(pivot.getR1()-purchase.get());
                purchase.set(0.0);
            }
        });
        LOGGER.info("Rising: "+rising+" falling: "+falling+" Profit: "+profit);
    }
}
