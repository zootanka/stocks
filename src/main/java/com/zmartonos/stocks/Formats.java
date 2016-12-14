package com.zmartonos.stocks;

import java.time.format.DateTimeFormatter;

/**
 * Created by zootanka on 28.07.16.
 */
public class Formats {
    static final DateTimeFormatter yyyyMMddFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static final DateTimeFormatter ddMMyyyyFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final DateTimeFormatter yyyyFormatter = DateTimeFormatter.ofPattern("yyyy");
}
