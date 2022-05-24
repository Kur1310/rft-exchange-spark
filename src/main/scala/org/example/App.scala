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

  val df = spark.read.option("delimiter",";").format("org.apache.spark.sql.execution.datasources.v2.csv.CSVDataSourceV2").load("gs://rtf-xchnage-spark-test/SparkJar/data.csv")


  //df.count()
  //df.printSchema()
  println(df.count())

  println("Updated jar")

  val ca = spark.read.option("multiline",true).format("org.apache.spark.sql.execution.datasources.v2.json.JsonDataSourceV2").load("gs://rtf-xchnage-spark-test/SparkJar/ca.json")
  val pty = spark.read.format("org.apache.spark.sql.execution.datasources.v2.json.JsonDataSourceV2").load("gs://rtf-xchnage-spark-test/SparkJar/alt.json")

  ca.show(false)
  //pty.count()
  //pty.printSchema()
  println(ca.count())
  println(pty.count())

  ca.repartition(10).write.mode("Overwrite").format("org.apache.spark.sql.execution.datasources.v2.json.JsonDataSourceV2").save("gs://rtf-xchnage-spark-test/Output/customer.json")


}
