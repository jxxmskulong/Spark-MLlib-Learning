package xingoo.core

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by xinghailong on 2017/6/21.
  */
object AccumulatorTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[2]").setAppName("accumulator-test")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    var accum = sc.accumulator(0)
    val rdd = sc.parallelize(List("a","b","c","d"),3)
    rdd.map(a => accum.setValue(accum.value+1)) // 不好使

     sc.parallelize(1 to 10).foreach(x=> accum += 1) // 没毛病
    println(accum.value)
  }
}
