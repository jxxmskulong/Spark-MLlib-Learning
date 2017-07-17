package xingoo.mllib

import org.apache.spark.mllib.classification.LogisticRegressionWithSGD
import org.apache.spark.mllib.linalg.{SparseVector, Vectors}
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by xinghailong on 2017/6/12.
  */
object LRTest {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("MovieLensALS-Test").setMaster("local[2]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val parsedData = sc.textFile("C:\\Users\\xinghailong\\Documents\\workspace\\my\\Spark-MLlib-Learning\\resouce\\lr_test").map(line => {
      val row = line.split("\\|")
      LabeledPoint(row(0).toDouble, Vectors.dense(row(1).split(' ').map(_.toDouble)))
    }).cache()
    parsedData.foreach(println)
    val model = LogisticRegressionWithSGD.train(parsedData, 20) //建立模型
    val target = Vectors.dense(20,30) //创建测试值
    val resulet = model.predict(target) //根据模型计算结果

    val margin = dot(model.weights.toSparse, target.toSparse) + model.intercept
    val score = 1.0 / (1.0 + math.exp(-margin))
    println(score)
    println("model.weights:")
    println(model.weights)
    println(resulet) //打印结果
    model.clearThreshold()
    println(model.predict(Vectors.dense(10)))
    sc.stop()
  }

  def dot(x: SparseVector, y: SparseVector): Double = {
    val xValues = x.values
    val xIndices = x.indices
    val yValues = y.values
    val yIndices = y.indices
    val nnzx = xIndices.size
    val nnzy = yIndices.size

    var kx = 0
    var ky = 0
    var sum = 0.0
    // y catching x
    while (kx < nnzx && ky < nnzy) {
      val ix = xIndices(kx)
      while (ky < nnzy && yIndices(ky) < ix) {
        ky += 1
      }
      if (ky < nnzy && yIndices(ky) == ix) {
        sum += xValues(kx) * yValues(ky)
        ky += 1
      }
      kx += 1
    }
    sum
  }
}
