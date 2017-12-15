package xingoo.ml.tranformer

import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.sql.SparkSession

object VectorIndexerTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("vector-indexer").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val data = spark.read
      .format("libsvm")
      .load("/Users/xingoo/IdeaProjects/Spark-MLlib-Learning/src/xingoo/ml/tranformer/data.txt")

    val indexer = new VectorIndexer()
      .setInputCol("features")
      .setOutputCol("indexed")
      .setMaxCategories(2)

    val indexerModel = indexer.fit(data)

    val categoricalFeatures: Set[Int] = indexerModel.categoryMaps.keys.toSet
    println(s"Chose ${categoricalFeatures.size} categorical features: " +
      categoricalFeatures.mkString(", "))

    // Create new column "indexed" with categorical values transformed to indices
    val indexedData = indexerModel.transform(data)
    indexedData.show(false)
  }
}
