package xingoo.mllib.pipeline

import org.apache.spark.ml.feature.Binarizer
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * binarizer是通过设置的阈值，把数据转成0或者1
  *
  * Created by xinghailong on 2017/6/19.
  */
object BinarizerTest {
  def main(args: Array[String]) {

    val conf = new SparkConf().setMaster("local[*]").setAppName("NgramTest")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

//    val data = Array((0, 0.1), (1, 0.8), (2, 0.2))
    val data = Array((0, 1.0), (1, 8.0), (2, 2.0))
    val dataFrame: DataFrame = sqlContext.createDataFrame(data).toDF("label", "feature")

    val binarizer: Binarizer = new Binarizer()
      .setInputCol("feature")
      .setOutputCol("binarized_feature")
//      .setThreshold(0.5)
      .setThreshold(5.0)

    val binarizedDataFrame = binarizer.transform(dataFrame)
    val binarizedFeatures = binarizedDataFrame.select("binarized_feature")
    binarizedFeatures.collect().foreach(println)
  }
}
