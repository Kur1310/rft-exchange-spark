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


  //df.count()
  //df.printSchema()
  println(df.count())

  println("Updated jar")

  val ca = spark.read.format("org.apache.spark.sql.execution.datasources.v2.json.JsonDataSourceV2").load("gs://ac-rftexchange-qa-project-dataproc-staging/SparkJar/ca.json")
  val pty = spark.read.format("org.apache.spark.sql.execution.datasources.v2.json.JsonDataSourceV2").load("gs://ac-rftexchange-qa-project-dataproc-staging/SparkJar/alt.json")

  //pty.count()
  //pty.printSchema()
  println(ca.count())
  println(pty.count())


}
