package com.zmartonos.stocks;

import com.google.common.collect.Lists;

import java.io.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zootanka on 28.07.16.
 */
public class DailyPriceCSVReader {

    public static List<Price> readDailyPriceCSV(final String fileName) throws IOException {
        final List<Price> prices = Lists.newArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            String[] parts;
            while ((line = br.readLine()) != null) {
                parts = line.split("\\s");
                prices.add(new Price(LocalDate.parse(parts[0],Formats.ddMMyyyyFormatter),
                        Double.valueOf(parts[1].replace(",", ".")), Double.valueOf(parts[2].replace(",", ".")),
                        Double.valueOf(parts[3].replace(",", ".")), Double.valueOf(parts[4].replace(",", ".")),
                        Long.valueOf(parts[5].replace(".",""))));
            }
        }

        Collections.sort(prices, new Comparator<Price>() {
            @Override
            public int compare(Price o1, Price o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        return prices;
    }
}
