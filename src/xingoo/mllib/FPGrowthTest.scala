package xingoo.mllib

import org.apache.spark.mllib.fpm.FPGrowth
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by xinghailong on 2017/7/17.
  */
object FPGrowthTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("MovieLensALS-Test").setMaster("local[2]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    val data_path = "C:\\Users\\xinghailong\\Documents\\workspace\\my\\Spark-MLlib-Learning\\resouce\\fp_grouwth_data.txt"
    val data = sc.textFile(data_path)
    val examples = data.map(_.split(" ")).cache()

    val minSupport = 0.2
    val numPartition = 10
    val model = new FPGrowth().setMinSupport(minSupport).setNumPartitions(numPartition).run(examples)

    println(s"Number of frequent itemsets: ${model.freqItemsets.count()}")
    model.freqItemsets.collect().foreach{ itemset =>
      println(itemset.items.mkString("[",",","]")+","+itemset.freq)
    }

    sc.stop()
  }
}
