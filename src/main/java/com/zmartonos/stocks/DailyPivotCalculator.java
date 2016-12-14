package com.zmartonos.stocks;

/**
 * Created by zootanka on 28.07.16.
 */
public class DailyPivotCalculator {
    public static DailyPivot calculateDailyPivot(final Price prevDayPrice, Price dayPrice){
        double pivot = (prevDayPrice.getHigh() + prevDayPrice.getLow() + prevDayPrice.getClose())/3;
        double r1 = 2 * pivot - prevDayPrice.getLow();
        double s1 = 2 * pivot - prevDayPrice.getHigh();
        double r2 = pivot - s1 + r1;
        double s2 = pivot - r1 + s1;
        double r3 = pivot - s2 + r2;
        double s3 = pivot - r2 + s2;
        double r4 = pivot + r3 - s3;
        double s4 = pivot - r3 + s3;

        return new DailyPivot(prevDayPrice, dayPrice, r1,r2,r3,r4,s1,s2,s3,s4);
    }
}
