package xingoo.basic

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.{CoordinateMatrix, IndexedRow, IndexedRowMatrix, MatrixEntry}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by xinghailong on 2017/6/8.
  */
object CoordinateRowMatrixTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("RowMatrixTest")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val rdd = sc.textFile("C:\\Users\\xinghailong\\Documents\\workspace\\my\\Spark-MLlib-Learning\\resouce\\row_matrix_data")
      .map(_.split(" "))
      .map(_.map(_.toDouble))
      .map(arr => (arr(0).toLong,arr(1).toLong,arr(2)))
      .map(vue => new MatrixEntry(vue._1,vue._2,vue._3))

    val rm = new CoordinateMatrix(rdd)
    println(rm.numRows()) //行数
    println(rm.numCols()) //列数
    println(rm.getClass)
    println(rm.entries.foreach(println))
  }
}
