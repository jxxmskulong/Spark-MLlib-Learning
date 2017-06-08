package xingoo.basic

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by xinghailong on 2017/6/8.
  */
object RowMatrixTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("RowMatrixTest")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val rdd = sc.textFile("C:\\Users\\xinghailong\\Documents\\workspace\\my\\Spark-MLlib-Learning\\resouce\\row_matrix_data")
      .map(_.split(" "))
      .map(_.map(_.toDouble))
      .map(line => Vectors.dense(line))

    val rm = new RowMatrix(rdd)
    println(rdd.getNumPartitions)

    //行数
    println(rm.numRows())
    //列数
    println(rm.numCols())

    val gm = new RowMatrix(rdd).computeGramianMatrix()
    println(gm.numRows)
    println(gm.numCols)
    println(gm.toString())

  }
}
