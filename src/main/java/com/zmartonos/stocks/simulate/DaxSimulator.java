package com.zmartonos.stocks.simulate;

import com.google.common.collect.Maps;
import com.zmartonos.stocks.DaxYearlyPriceCSVReader;
import com.zmartonos.stocks.Price;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by zmartonos on 14.12.16.
 */
public class DaxSimulator {
    private static final Logger LOGGER = LogManager.getLogger();

    private int currentYear;

    public static void main(final String[] argv) throws Exception {
        new DaxSimulator();
    }

    public DaxSimulator() throws Exception {
        LOGGER.info("Starting DailyPivotSimulator...");

        // read yearly prices
        final List<Price> prices = DaxYearlyPriceCSVReader.readYearlyPriceCSV(
                DailyPivotSimulator.class.getClassLoader().getResource("prices/dax.csv").getFile());

        final Optional<Integer> max = prices.stream().map(e->e.getDate().getYear()).max(Integer::compareTo);

        //currentYear = max.orElseThrow(()->new Exception("Something went wrong")) + 1;
        currentYear = 2041;

        final List<DaxFutureSimulation> simulations = prices.stream()
                .map(e->DaxSimulator.simulate(e, currentYear))
                .collect(Collectors.toList());

        simulations.forEach(LOGGER::info);
    }

    public static DaxFutureSimulation simulate(final Price price, final int currentYear){
        final int year = price.getDate().getYear();
        final DaxFutureSimulation simulation = new DaxFutureSimulation(price);
        final Map<Integer, Long> map = Maps.newTreeMap();

        map.put(year, (long)price.getClose());

        for(int i = year+1 ; i < currentYear; i ++ ){
            map.put(i, (long)(map.get(i-1) * 1.05));
        }
        map.remove(year);

        simulation.setSimulation(map);
        return simulation;
    }
}
