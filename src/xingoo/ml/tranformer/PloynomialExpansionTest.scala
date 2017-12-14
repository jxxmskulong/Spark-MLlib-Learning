package xingoo.ml.tranformer

import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.PolynomialExpansion
import org.apache.spark.ml.linalg.Vectors

object PloynomialExpansionTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("ploynomial").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val data = Array(
      Vectors.dense(2.0, 1.0),
      Vectors.dense(0.0, 0.0),
      Vectors.dense(3.0, -1.0)
    )
    val df = spark.createDataFrame(data.map(Tuple1.apply)).toDF("features")

    val polyExpansion = new PolynomialExpansion()
      .setInputCol("features")
      .setOutputCol("polyFeatures")
      .setDegree(2)

    val polyDF = polyExpansion.transform(df)
    polyDF.show(false)
  }
}
