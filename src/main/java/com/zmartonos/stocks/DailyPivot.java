package com.zmartonos.stocks;

/**
 * Created by zootanka on 28.07.16.
 */
public class DailyPivot {
    private final DailyPrice prevDayPrice;
    private final DailyPrice dayPrice;
    private double r1;
    private double r2;
    private double r3;
    private double r4;

    private double s1;
    private double s2;
    private double s3;
    private double s4;

    public DailyPivot(DailyPrice prevDayPrice, DailyPrice dayPrice, double r1, double r2, double r3, double r4,
                      double s1, double s2, double s3, double s4) {
        this.prevDayPrice = prevDayPrice;
        this.dayPrice = dayPrice;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
    }

    public double getR1() {
        return r1;
    }

    public double getR2() {
        return r2;
    }

    public double getR3() {
        return r3;
    }

    public double getR4() {
        return r4;
    }

    public double getS1() {
        return s1;
    }

    public double getS2() {
        return s2;
    }

    public double getS3() {
        return s3;
    }

    public double getS4() {
        return s4;
    }

    public DailyPrice getPrevDayPrice() {
        return prevDayPrice;
    }

    public DailyPrice getDayPrice() {
        return dayPrice;
    }

    @Override
    public String toString() {
        return "DailyPivot{" +
                "prevDayPrice=" + prevDayPrice +
                ", dayPrice=" + dayPrice +
                ", r1=" + r1 +
                ", r2=" + r2 +
                ", r3=" + r3 +
                ", r4=" + r4 +
                ", s1=" + s1 +
                ", s2=" + s2 +
                ", s3=" + s3 +
                ", s4=" + s4 +
                '}';
    }
}
