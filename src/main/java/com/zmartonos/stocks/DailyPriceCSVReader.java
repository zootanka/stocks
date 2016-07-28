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

    public static List<DailyPrice> readDailyPriceCSV(final String fileName) throws IOException {
        final List<DailyPrice> dailyPrices = Lists.newArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            String[] parts;
            while ((line = br.readLine()) != null) {
                parts = line.split("\\s");
                dailyPrices.add(new DailyPrice(LocalDate.parse(parts[0],Formats.ddMMyyyyFormatter),
                        Double.valueOf(parts[1].replace(",", ".")), Double.valueOf(parts[2].replace(",", ".")),
                        Double.valueOf(parts[3].replace(",", ".")), Double.valueOf(parts[4].replace(",", ".")),
                        Long.valueOf(parts[5].replace(".",""))));
            }
        }

        Collections.sort(dailyPrices, new Comparator<DailyPrice>() {
            @Override
            public int compare(DailyPrice o1, DailyPrice o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        return dailyPrices;
    }
}
