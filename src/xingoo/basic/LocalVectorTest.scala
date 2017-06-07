package xingoo.basic

import org.apache.spark.mllib.linalg.Vectors

/**
  * 本能向量
  *
  * Created by xinghailong on 2017/6/7.
  */
object LocalVectorTest {
  def main(args: Array[String]) {
    val vd = Vectors.dense(0,1,2,3,4)
    println(vd(1))

    //第一个参数是个数、第二个参数是下标的值，第三个参数是对应的值
    val vs = Vectors.sparse(4,Array(1,2,3,4),Array(5,6,7,8))
    println(vs(0))
  }
}
