package xingoo.basic

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.{IndexedRow, IndexedRowMatrix, RowMatrix}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by xinghailong on 2017/6/8.
  */
object IndexedRowMatrixTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("RowMatrixTest")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val rdd = sc.textFile("C:\\Users\\xinghailong\\Documents\\workspace\\my\\Spark-MLlib-Learning\\resouce\\row_matrix_data")
      .map(_.split(" "))
      .map(_.map(_.toDouble))
      .map(line => Vectors.dense(line))
      .map((vd) => new IndexedRow(vd.size,vd))

    val rm = new IndexedRowMatrix(rdd)
    println(rm.numRows()) //行数
    println(rm.numCols()) //列数
    println(rm.getClass)
    println(rm.rows.foreach(println))
  }
}
