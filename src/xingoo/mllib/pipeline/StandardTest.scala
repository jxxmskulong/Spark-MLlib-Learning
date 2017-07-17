package xingoo.mllib.pipeline

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.feature.StandardScaler
/**
  * Created by xinghailong on 2017/6/19.
  */
object StandardTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[*]").setAppName("StandardTest")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val dataFrame = sqlContext.read.format("libsvm").load("C:\\Users\\xinghailong\\Documents\\workspace\\my\\Spark-MLlib-Learning\\resouce\\sample_libsvm_data.txt")

    val scaler = new StandardScaler()
      .setInputCol("features")
      .setOutputCol("scaledFeatures")
      .setWithStd(true)
      .setWithMean(false)

    // Compute summary statistics by fitting the StandardScaler.
    val scalerModel = scaler.fit(dataFrame)
    println(scalerModel.mean)
    println(scalerModel.std)
    println(scalerModel.uid)
    // Normalize each feature to have unit standard deviation.
    val scaledData = scalerModel.transform(dataFrame)
    scaledData.show()
  }
}
