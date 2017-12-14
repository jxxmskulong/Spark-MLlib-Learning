package xingoo.ml.tranformer

import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.Imputer

object ImputerTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("binarizer").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val df = spark.createDataFrame(Seq(
      (1.0, Double.NaN),
      (2.0, Double.NaN),
      (Double.NaN, 3.0),
      (4.0, 4.0),
      (5.0, 5.0)
    )).toDF("a", "b")

    val imputer = new Imputer()
      .setInputCols(Array("a", "b"))
      .setOutputCols(Array("out_a", "out_b"))
      .setMissingValue(Double.NaN)//指定针对哪个值进行设置，默认是Double.NaN
      .setStrategy("mean")//mean 或者 median

    val model = imputer.fit(df)
    model.transform(df).show()
  }
}
