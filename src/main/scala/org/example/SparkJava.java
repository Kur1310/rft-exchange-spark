package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SparkJava {
    private static final Logger LOGGER = LoggerFactory.getLogger(SparkJava.class);
    public static void main(String[] args) {

        String master ="local[*]";

        SparkSession spark =SparkSession.builder().appName("SparkJava").master(master).getOrCreate();
        LOGGER.info("Reading JSON file --Start");
        Dataset<Row> ca = spark.read().option("multiline",true).
                format("org.apache.spark.sql.execution.datasources.json.JsonFileFormat").load("gs://rtf-xchnage-spark-test/SparkJar/ca.json");
        LOGGER.info("Reading JSON file --END");
        ca.printSchema();
        LOGGER.info("Show JSON Dataset file");
        ca.show();

    }
}
