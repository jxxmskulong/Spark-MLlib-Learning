package xingoo.mllib.pipeline

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.feature.StopWordsRemover
import org.apache.spark.sql.SQLContext

/**
  * 移除停顿词，停顿词的列表参考：http://ir.dcs.gla.ac.uk/resources/linguistic_utils/stop_words
  *
  * Created by xinghailong on 2017/6/19.
  */
object StopTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[*]").setAppName("StopTest")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val remover = new StopWordsRemover()
      .setInputCol("raw")
      .setOutputCol("filtered")

    val dataSet = sqlContext.createDataFrame(Seq(
      (0, Seq("I", "saw", "the", "red", "baloon")),
      (1, Seq("Mary", "had", "a", "little", "lamb"))
    )).toDF("id", "raw")

    remover.transform(dataSet).show()
  }
}
