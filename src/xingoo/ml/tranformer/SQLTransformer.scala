package xingoo.ml.tranformer

import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.SQLTransformer

object SQLTransformer {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("binarizer").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")


    val df = spark.createDataFrame(
      Seq((0, 1.0, 3.0), (2, 2.0, 5.0))).toDF("id", "v1", "v2")

    val sqlTrans = new SQLTransformer().setStatement(
      "SELECT *, (v1 + v2) AS v3, (v1 * v2) AS v4 FROM __THIS__")

    sqlTrans.transform(df).show()

    // 验证是否支持关联
    val df1 = spark.createDataFrame(
      Seq((0,"a"),(1,"b"),(2,"c"))).toDF("id","tag").createOrReplaceTempView("ttt")

    val sqlTrans1 = new SQLTransformer().setStatement(
      "SELECT * FROM __THIS__  a left join ttt b on a.id = b.id")

    sqlTrans1.transform(df).show(false)
  }
}
