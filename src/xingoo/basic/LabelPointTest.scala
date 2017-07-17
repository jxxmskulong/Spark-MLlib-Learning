package xingoo.basic

import org.apache.spark.mllib.linalg.{SparseVector, Vectors}
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
    println("~~~~~~~~~")
    val vs1 = new SparseVector(1,Array(1),Array(1))
    val vs2 = new SparseVector(2,Array(1,2),Array(2,3))
    val vs3 = vs1.indices ++ vs2.indices
    val vs4 = vs1.values ++ vs2.values
    val sp = new SparseVector(vs3.size+vs4.size,vs3,vs4)
    vs3.foreach(println(_))
    println(sp)
  }
}
