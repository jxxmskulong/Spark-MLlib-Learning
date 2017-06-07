package xingoo.basic

import org.apache.spark.mllib.linalg.Matrices

/**
  * 把一维数组转变成指定的矩阵
  *
  * Created by xinghailong on 2017/6/7.
  */
object LocalMatrixTest {
  def main(args: Array[String]) {
    val mx = Matrices.dense(2,3,Array(1,2,3,4,5,6))
    println(mx)
  }
}
