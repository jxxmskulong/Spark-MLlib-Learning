package xingoo.basic

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint

/**
  * 向量标签
  *
  * Created by xinghailong on 2017/6/7.
  */
object LabelPointTest {
  def main(args: Array[String]) {
    val vd = Vectors.dense(0,1,2,3,4)
    val vdLable = LabeledPoint(3,vd)

    println(vdLable.features)
    println(vdLable.label)

    val vs = Vectors.sparse(4,Array(1,2,3,4),Array(5,6,7,8))
    val vsLable = LabeledPoint(2,vs)

    println(vsLable.features)
    println(vsLable.label)

    println(vsLable.features(2))
  }
}
