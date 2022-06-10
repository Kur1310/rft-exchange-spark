package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class JavaExample {
    public static void main (String[] args) {
        System.out.println("Loading CSV file");
        SparkSession spark = SparkSession.builder().getOrCreate();

        Dataset df = spark.read()
                            .option("delimiter",";")
                            .format("org.apache.spark.sql.execution.datasources.v2.csv.CSVDataSourceV2")
                            .load("gs://rtf-xchnage-spark-test/SparkJar/data.csv");

        System.out.println("The count is : " + df.count());

        Dataset caDF = spark.read()
                            .option("multiline",true)
                            .format("org.apache.spark.sql.execution.datasources.v2.json.JsonDataSourceV2")
                            .load("gs://rtf-xchnage-spark-test/SparkJar/ca.json");
        System.out.println("The count is : " + caDF.count());

        Dataset ptyDF = spark.read()
                                .format("org.apache.spark.sql.execution.datasources.v2.json.JsonDataSourceV2")
                                .load("gs://rtf-xchnage-spark-test/SparkJar/alt.json");
        System.out.println("The count is : " + ptyDF.count());

        df.show();
        caDF.show();
        ptyDF.show();
    }
}
