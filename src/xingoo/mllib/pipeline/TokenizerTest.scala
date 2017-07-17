package xingoo.mllib.pipeline
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.feature.{RegexTokenizer, Tokenizer}
import org.apache.spark.sql.SQLContext
/**
  *
  *
  * Created by xinghailong on 2017/6/19.
  */
object TokenizerTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[*]").setAppName("TokenizerTest")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val sentenceDataFrame = sqlContext.createDataFrame(Seq(
      (0, "Hi I heard about Spark"),
      (1, "I wish Java could use case classes"),
      (2, "Logistic,regression,models,are,neat"),
      (3, "Hello.world")
    )).toDF("label", "sentence")

    val tokenizer = new Tokenizer().setInputCol("sentence").setOutputCol("words")
    val regexTokenizer = new RegexTokenizer()
      .setInputCol("sentence")
      .setOutputCol("words")
      .setPattern("\\W") // alternatively .setPattern("\\w+").setGaps(false)

    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~")
    val tokenized = tokenizer.transform(sentenceDataFrame)
    tokenized.select("words", "label").take(4).foreach(println)
    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~")
    val regexTokenized = regexTokenizer.transform(sentenceDataFrame)
    regexTokenized.select("words", "label").take(4).foreach(println)
    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~")
  }
}
