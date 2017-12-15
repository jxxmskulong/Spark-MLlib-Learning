package xingoo.ml.tranformer

import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.Bucketizer

object BucketizerTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("binarizer").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

//    val splits = Array(Double.NegativeInfinity, -0.5, 0.0, 0.5, Double.PositiveInfinity)
//    val splits1= Array(-0.5, 0.0, 0.5)
    val splits2 = Array(Double.NegativeInfinity,15,20,25,30,35,40,45,50,55,60,Double.PositiveInfinity)
// [-inf,15) [15,20)
//    val data = Array(-999.9, -0.5, -0.3, 0.0, 0.2, 999.9)
    val data1 = Array(10.0,15.0,33.0,40.0)
    val dataFrame = spark.createDataFrame(data1.map(Tuple1.apply)).toDF("features")

    val bucketizer = new Bucketizer()
      .setInputCol("features")
      .setOutputCol("bucketedFeatures")
      .setSplits(splits2)

    // Transform original data into its bucket index.
    val bucketedData = bucketizer.transform(dataFrame)

    println(s"Bucketizer output with ${bucketizer.getSplits.length-1} buckets")
    bucketedData.show()
  }
}
