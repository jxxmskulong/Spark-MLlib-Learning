package xingoo.ml.tranformer

import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

object VectorAssembler {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("binarizer").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val dataset = spark.createDataFrame(
      Seq((0, 18, 1.0, Vectors.dense(0.0, 10.0, 0.5), 1.0,true))
    ).toDF("id", "hour", "mobile", "userFeatures", "clicked","test")
    dataset.show()

    val assembler = new VectorAssembler()
      .setInputCols(Array("hour", "mobile", "userFeatures","test"))
      .setOutputCol("features")

    val output = assembler.transform(dataset)
    println("Assembled columns 'hour', 'mobile', 'userFeatures' to vector column 'features'")
    output.select("features", "clicked").show(false)
  }
}
