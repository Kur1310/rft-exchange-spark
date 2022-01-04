package org.example

/**
 * Hello world!
 *
 */

import org.apache.spark.sql.SparkSession

object App extends App {
  println( "Hello World!" )

  val spark = SparkSession.builder.getOrCreate()
  val columns = Seq("language","users_count")
  val data = Seq(("Java", "20000"), ("Python", "100000"), ("Scala", "3000"))

  val df = spark.read.option("delimiter",";").format("org.apache.spark.sql.execution.datasources.v2.csv.CSVDataSourceV2").load("gs://ac-rftexchange-qa-project-dataproc-staging/SparkJar/data.csv")


  df.printSchema()
  val rdd = spark.sparkContext.parallelize(data)
  val dfFromRDD2 = spark.createDataFrame(rdd).toDF(columns:_*)


  dfFromRDD2.printSchema()



}
