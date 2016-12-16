package com.zmartonos.stocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zmartonos on 16.12.16.
 */
public class SparkML {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(final String[] argv) throws Exception{
        System.setProperty("log4j.configuration", "file:/usr/share/devdata/log4j.properties");
        SparkSession spark = SparkSession
                .builder()
                .master("local")
                .appName("Java Spark SQL basic example")
                .getOrCreate();

        Dataset<Row> dfDax = readCSV(spark, "/usr/share/devdata/prices/dax.csv");
        Dataset<Row> dfGdp = readCSV(spark, "/usr/share/devdata/prices/ger_gdp.csv");
        Dataset<Row> dfInflation = readCSV(spark, "/usr/share/devdata/prices/ger_inflation.csv");
        Dataset<Row> df10YBund = readCSV(spark, "/usr/share/devdata/prices/ger_10ybund.csv");

        final Dataset<Row> df = dfDax.join(dfGdp, "year").join(dfInflation, "year").join(df10YBund, "year");

        df.createTempView("dax");

        final List<String> columns = Arrays.asList(df.columns());
        LOGGER.info(Arrays.asList(df.columns())+" :: "+df.columns().length);

        Dataset<Row> sql = df.sqlContext().sql("SELECT * FROM dax WHERE percent < 0");

        LOGGER.info(sql.count());
    }

    public static Dataset<Row> readCSV(final SparkSession spark, final String csv){
        Dataset<Row> dfDax = spark.read()
                .option("header", "true")
                .option("inferSchema", "true")
                .option("delimiter", "\t")
                .csv(csv);

        return dfDax;
    }
}
