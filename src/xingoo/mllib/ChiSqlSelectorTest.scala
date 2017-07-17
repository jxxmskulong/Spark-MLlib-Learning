package xingoo.mllib

import org.apache.spark.ml.feature.ChiSqSelector
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by xinghailong on 2017/7/11.
  */
object ChiSqlSelectorTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("MovieLensALS-Test").setMaster("local[2]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    var sqlContext = new SQLContext(sc)

    val data = Seq(
      (7, Vectors.dense(0.0, 0.0, 18.0, 1.0), 1.0),
      (8, Vectors.dense(0.0, 1.0, 12.0, 0.0), 0.0),
      (9, Vectors.dense(1.0, 0.0, 15.0, 0.1), 0.0)
    )

    val beanRDD = sc.parallelize(data).map(t3 => Bean(t3._1,t3._2,t3._3))
    val df = sqlContext.createDataFrame(beanRDD)

    val selector = new ChiSqSelector()
      .setNumTopFeatures(2)
      .setFeaturesCol("features")
      .setLabelCol("clicked")
      .setOutputCol("selectedFeatures")

    val result = selector.fit(df).transform(df)
    result.show()
  }

  case class Bean(id:Double,features:org.apache.spark.mllib.linalg.Vector,clicked:Double){}
}
