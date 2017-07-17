package xingoo.mllib

import org.apache.spark.ml.feature.{OneHotEncoder, StringIndexer}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by xinghailong on 2017/7/4.
  */
object encoderTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("MovieLensALS-Test").setMaster("local[2]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val sqlContext = new SQLContext(sc)
    val df = sqlContext.createDataFrame(Seq(
      (60, 170,"F","长春"),
      (45, 163,"M","长春"),
      (80, 183,"F","沈阳"),
      (70, 175,"F","大连"),
      (52, 167,"M","哈尔滨")
    )).toDF("weight", "height","sex","address")

    df.printSchema()
    df.schema.indexOf("sex")

    val indexer = new StringIndexer()
      .setInputCol("sex")
      .setOutputCol("sexIndex")
      .fit(df)
    val indexed = indexer.transform(df)

    val encoder = new OneHotEncoder()
      .setInputCol("sexIndex")
      .setOutputCol("sexVec")
    val encoded = encoder.transform(indexed)

    val indexer1 = new StringIndexer()
      .setInputCol("address")
      .setOutputCol("addressIndex")
      .fit(encoded)
    val indexed1 = indexer1.transform(encoded)

    val encoder1 = new OneHotEncoder()
      .setInputCol("addressIndex")
      .setOutputCol("addressVec")
    val encoded1 = encoder1.transform(indexed1)

    encoded1.show()
  }
}
