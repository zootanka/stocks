package com.zmartonos.stocks;

import org.apache.commons.csv.CSVFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zmartonos on 14.12.16.
 */
public class DaxYearlyPriceCSVReader {
    private static final Logger LOGGER = LogManager.getLogger();

    public static List<Price> readYearlyPriceCSV(final String fileName) throws IOException {
        try (final Reader in = new FileReader(fileName)) {
            return CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in).getRecords().stream().map(
                    e -> {LOGGER.info(e.toString());
                        return new Price(LocalDate.parse(e.get("Jahr"), Formats.yyyyFormatter),
                            Double.valueOf(e.get("Jahresstart").replace(",", ".")),
                            Double.valueOf(e.get("Jahresende").replace(",", ".")),
                            Double.valueOf(e.get("Jahrestief").replace(",", ".")),
                            Double.valueOf(e.get("Jahreshoch").replace(",", ".")),
                            0L);}
            ).collect(Collectors.toList());
        }
    }
}
