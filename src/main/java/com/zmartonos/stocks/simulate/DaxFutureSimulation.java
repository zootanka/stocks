package com.zmartonos.stocks.simulate;

import com.zmartonos.stocks.Price;

import java.util.Map;

/**
 * Created by zootanka on 15.12.16.
 */
public class DaxFutureSimulation {
    private Price price;
    private Map<Integer, Long> simulation;

    public DaxFutureSimulation(Price price) {
        this.price = price;
    }

    public Map<Integer, Long> getSimulation() {
        return simulation;
    }

    public void setSimulation(Map<Integer, Long> simulation) {
        this.simulation = simulation;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DaxFutureSimulation{" +
                "year=" + price.getDate().getYear() +
                ", close price=" + price.getClose() +
                ", simulation=" + simulation +
                '}';
    }
}
