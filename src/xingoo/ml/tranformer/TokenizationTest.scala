package xingoo.ml.tranformer

import org.apache.spark.ml.feature.{RegexTokenizer, Tokenizer}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object TokenizationTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("tf-idf").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val sentenceDataFrame = spark.createDataFrame(Seq(
      (0, "Hi I heard about Spark"),
      (1, "I wish Java could use case classes"),
      (2, "Logi'stic,regression,models,are, neat")
    )).toDF("id", "sentence")

    val tokenizer = new Tokenizer().setInputCol("sentence").setOutputCol("words")
    val regexTokenizer = new RegexTokenizer()
      .setInputCol("sentence")
      .setOutputCol("words")
      .setPattern("\\w+") // alternatively .setPattern("\\w+").setGaps(false)
      .setGaps(false)//如果为false，那么pattern代表的是token；为true，pattern代表的是delimiter

    val countTokens = udf { (words: Seq[String]) => words.length }

    val tokenized = tokenizer.transform(sentenceDataFrame)
    tokenized.select("sentence", "words")
      .withColumn("tokens", countTokens(col("words"))).show(false)

    val regexTokenized = regexTokenizer.transform(sentenceDataFrame)
    regexTokenized.select("sentence", "words")
      .withColumn("tokens", countTokens(col("words"))).show(false)
  }
}
