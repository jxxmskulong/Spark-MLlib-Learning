package xingoo.mllib

import org.apache.spark.ml.feature.StopWordsRemover
import org.apache.spark.sql.SparkSession

object StopWordsTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .master("local")
      .appName("StopWordsRemoverExample")
      .getOrCreate()

    // $example on$
    val remover = new StopWordsRemover()
      .setInputCol("raw")
      .setOutputCol("filtered")

    //待扣词的原始文本
    val dataSet = spark.createDataFrame(Seq(
      (0, Seq("I", "saw", "the", "red", "balloon")),
      (1, Seq("Mary", "had", "a", "little", "lamb"))
    )).toDF("id", "raw")

    // 直接过滤掉停顿词，org/apache/spark/ml/feature/stopwords
    // 有默认的停顿词的词库，默认走的是英文的停顿词
    // 其实就是读取停顿词，形成set，然后执行xxx.filter(!set.contains(_))
    remover.transform(dataSet).show(false)
    // $example off$

    spark.stop()
  }
}
