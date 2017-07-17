package xingoo.mllib.pipeline

import org.apache.spark.ml.feature.{OneHotEncoder, StringIndexer}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 转变成稀疏向量
  *
  * Created by xinghailong on 2017/6/19.
  */
object OneHotEncoderTest {
  def main(args: Array[String]) {

    val conf = new SparkConf().setMaster("local[*]").setAppName("OneHotEncoderTest")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val df = sqlContext.createDataFrame(Seq(
      (0, "a"),
      (1, "b"),
      (2, "c"),
      (3, "a"),
      (4, "a"),
      (5, "c"),
      (6, "d")
    )).toDF("id", "category")

    val indexer = new StringIndexer()
      .setInputCol("category")
      .setOutputCol("categoryIndex")
      .fit(df)
    val indexed = indexer.transform(df)

    val encoder = new OneHotEncoder()
      .setInputCol("categoryIndex")
      .setOutputCol("categoryVec")
    val encoded = encoder.transform(indexed)
    encoded.select("id", "categoryVec").show()
    encoded.show()
  }
}
