package com.zmartonos.stocks;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by zootanka on 07.11.15.
 */
public class Price {
    private final LocalDate date;
    private final double open;
    private final double close;
    private final double high;
    private final double low;
    private final long volume;

    public Price(LocalDate date, double open, double close, double high, double low, long volume) {
        this.date = date;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getOpen() {
        return open;
    }

    public double getClose() {
        return close;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public long getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Price{" +
                "date=" + date +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                '}';
    }
}
