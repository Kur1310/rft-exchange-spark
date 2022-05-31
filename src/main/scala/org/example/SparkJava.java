package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
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
                format("org.apache.spark.sql.execution.datasources.json.JsonFileFormat")
                //.load("/Users/keyurshah/IdeaProjects/rft-exchange-spark/ca.json");
                .load("gs://rtf-xchnage-spark-test/SparkJar/ca.json");
        Dataset<Row> customerDf = spark.read().option("multiline",true).
                format("org.apache.spark.sql.execution.datasources.json.JsonFileFormat")
                //.load("/Users/keyurshah/IdeaProjects/rft-exchange-spark/sparkjava.json");
                .load("gs://rtf-xchnage-spark-test/SparkJar/sparkjava.json");
        Dataset<Row> joinedDf = ca.join(customerDf, ca.col("id")
                .equalTo(customerDf.col("id")), "left_outer");
        LOGGER.info("Reading JSON file --END");
        joinedDf = joinedDf.select(joinedDf.col("customerName").alias("CUS_NAME"),
                joinedDf.col("customerEmail"),
                joinedDf.col("businessUnitName"));
        LOGGER.info("Show JSON Dataset file");
        joinedDf.show();
        joinedDf.write()
                .option("hearder", "true")
                .format("org.apache.spark.sql.execution.datasources.csv.CSVFileFormat")
                .mode("Overwrite")
                .save("gs://rtf-xchnage-spark-test/Output/resultCus.csv");


    }
}
