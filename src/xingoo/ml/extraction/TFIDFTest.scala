package xingoo.ml.extraction

import org.apache.spark.ml.feature.{HashingTF, IDF, Tokenizer}
import org.apache.spark.sql.SparkSession

object TFIDFTest {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local[*]").appName("tf-idf").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val sentenceData = spark.createDataFrame(Seq(
      (0.0, "你 我 他"),
      (0.0, "你 他"),
      (1.0, "你")
    )).toDF("label", "sentence")

    val tokenizer = new Tokenizer().setInputCol("sentence").setOutputCol("words")
    val wordsData = tokenizer.transform(sentenceData)

    val hashingTF = new HashingTF()
      .setInputCol("words")
      .setOutputCol("rawFeatures")
      .setNumFeatures(10)//向量的维度
      .setBinary(true)//词频数量是否都为1

    val featurizedData = hashingTF.transform(wordsData)
    featurizedData.show(false)

    // alternatively, CountVectorizer can also be used to get term frequency vectors

    val idf = new IDF()
      .setInputCol("rawFeatures")
      .setOutputCol("features")
    val idfModel = idf.fit(featurizedData)

    val rescaledData = idfModel.transform(featurizedData)
    val result = rescaledData.select("label", "features")
    result.show(false)
  }
}
