package xingoo.ml.tranformer

import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.QuantileDiscretizer

object QuantileDiscretizer {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("binarizer").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val data1 = Array((0, 0.2), (1, 0.4), (2, 0.6), (3, 0.8), (4, 1.0))
    val data = Array((0, 18.0), (1, 19.0), (2, 8.0), (3, 5.0), (4, 2.2))
    val data2 = Array(
      (0,1),
      (0,2),
      (0,3),
      (0,4),
      (0,50),
      (0,100),
      (0,1000)
    )
    val data3 = Array(
      (0,1),
      (0,1),
      (0,1),
      (0,1),
      (0,2),
      (0,3)
    )
    val df = spark.createDataFrame(data3).toDF("id", "hour")

    val discretizer = new QuantileDiscretizer()
      .setInputCol("hour")
      .setOutputCol("result")
      .setNumBuckets(2)
      //.setRelativeError(0.1)

    val result = discretizer.fit(df).transform(df)
    result.show()
  }
}
