package xingoo.ml.tranformer

import org.apache.spark.ml.feature.StandardScaler
import org.apache.spark.sql.SparkSession

object StandardScalerTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("dct").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val data = spark.read
      .format("libsvm")
      .load("/Users/xingoo/IdeaProjects/Spark-MLlib-Learning/src/xingoo/ml/tranformer/data.txt")

    val scaler = new StandardScaler()
      .setInputCol("features")
      .setOutputCol("scaledFeatures")
      .setWithStd(false)
      .setWithMean(true)

    // Compute summary statistics by fitting the StandardScaler.
    val scalerModel = scaler.fit(data)

    // Normalize each feature to have unit standard deviation.
    val scaledData = scalerModel.transform(data)

    data.show(false)
    scaledData.show(false)
  }
}
