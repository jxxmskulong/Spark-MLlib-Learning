package xingoo.ml.extraction

import org.apache.spark.ml.feature.Word2Vec
import org.apache.spark.ml.linalg.Vector
import org.apache.spark.sql.{Row, SparkSession}

object Word2VecTest {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local[*]").appName("tf-idf").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    // Input data: Each row is a bag of words from a sentence or document.
    val documentDF = spark.createDataFrame(Seq(
      "猴子 喜欢 香蕉".split(" "),
      "海龙 喜欢 香蕉".split(" "),
      "海龙 喜欢 苹果".split(" ")
    ).map(Tuple1.apply)).toDF("text")

    val testDF = spark.createDataFrame(Seq(
      "猴子".split(" "),
      "海龙".split(" "),
      "香蕉".split(" "),
      "苹果".split(" ")
    ).map(Tuple1.apply)).toDF("text")

    // Learn a mapping from words to Vectors.
    val word2Vec = new Word2Vec()
      .setInputCol("text")
      .setOutputCol("result")
      .setVectorSize(3)
      .setMinCount(0)
    val model = word2Vec.fit(documentDF)

    val result = model.transform(testDF)
    result.collect().foreach { case Row(text: Seq[_], features: Vector) =>
      println(s"Text: [${text.mkString(", ")}] => \nVector: $features\n") }
  }
}
