package xingoo.basic

import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 这个没有成功，需要试一下
  *
  * Created by xinghailong on 2017/6/7.
  */
object LabelPoint2Test {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("LabelPoint2Test")
    val sc = new SparkContext(conf)
    val mu = MLUtils.loadLibSVMFile(sc,"C:\\Users\\xinghailong\\Documents\\workspace\\my\\Spark-MLlib-Learning\\resouce\\labeled_test_data")
    mu.foreach(println)
  }
}
