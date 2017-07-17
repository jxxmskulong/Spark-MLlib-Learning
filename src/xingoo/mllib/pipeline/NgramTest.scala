package xingoo.mllib.pipeline

import org.apache.spark.ml.feature.NGram
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by xinghailong on 2017/6/19.
  */
object NgramTest {
  def main(args: Array[String]) {

    val conf = new SparkConf().setMaster("local[*]").setAppName("NgramTest")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val wordDataFrame = sqlContext.createDataFrame(Seq(
      (0, Array("Hi", "I", "heard", "about", "Spark")),
      (1, Array("I", "wish", "Java", "could", "use", "case", "classes")),
      (2, Array("Logistic", "regression", "models", "are", "neat"))
    )).toDF("label", "words")

    val ngram = new NGram().setInputCol("words").setOutputCol("ngrams")
    val ngramDataFrame = ngram.transform(wordDataFrame)
    ngramDataFrame.take(3).map(_.getAs[Stream[String]]("ngrams").toList).foreach(println)
  }
}
