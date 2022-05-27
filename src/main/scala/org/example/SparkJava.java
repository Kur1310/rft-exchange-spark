package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkJava {
    public static void main(String[] args) {

        String master ="local[*]";

        SparkSession spark =SparkSession.builder().appName("SparkJava").master(master).getOrCreate();

        Dataset<Row> ca = spark.read().option("multiline",true).
                format("org.apache.spark.sql.execution.datasources.json.JsonFileFormat").load("gs://rtf-xchnage-spark-test/SparkJar/ca.json");
        ca.printSchema();
        ca.show();

    }
}
