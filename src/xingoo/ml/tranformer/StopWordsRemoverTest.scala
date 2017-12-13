package xingoo.ml.tranformer

import org.apache.spark.ml.feature.StopWordsRemover
import org.apache.spark.sql.SparkSession

object StopWordsRemoverTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("stop-remover").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val remover = new StopWordsRemover()
      .setInputCol("raw")
      .setOutputCol("filtered")
      .setStopWords(Array("你","我"))//添加自定义停顿词，默认为空
      .setCaseSensitive(false)//是否区分大小写，默认不区分大小写

    /**
      * languages: danish, dutch, english, finnish, french, german, hungarian,
      * italian, norwegian, portuguese, russian, spanish, swedish, turkish
      */
    StopWordsRemover.loadDefaultStopWords("english")

    val dataSet = spark.createDataFrame(Seq(
      (0, Seq("I", "saw", "the", "red", "balloon")),
      (1, Seq("Mary", "had", "a", "little", "lamb")),
      (2, Seq("你","在","哪里"))
    )).toDF("id", "raw")

    remover.transform(dataSet).show(false)
  }
}
