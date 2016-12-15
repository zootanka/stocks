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
            return CSVFormat.DEFAULT.withDelimiter('\t').withIgnoreSurroundingSpaces().withFirstRecordAsHeader().parse(in).getRecords().stream().map(
                    e -> new Price(LocalDate.parse(e.get("Jahr")+"0101", Formats.yyyyFormatter),
                            Double.valueOf(e.get("Jahresstart").replace(".", "").replace(",", ".")),
                            Double.valueOf(e.get("Jahresende").replace(".", "").replace(",", ".")),
                            Double.valueOf(e.get("Jahreshoch").replace(".", "").replace(",", ".")),
                            Double.valueOf(e.get("Jahrestief").replace(".", "").replace(",", ".")),
                            0L)
            ).collect(Collectors.toList());
        }
    }
}
