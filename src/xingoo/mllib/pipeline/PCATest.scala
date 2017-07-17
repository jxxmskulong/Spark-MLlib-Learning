package xingoo.mllib.pipeline
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.feature.PCA
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.sql.SQLContext
/**
  * Created by xinghailong on 2017/6/19.
  */
object PCATest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[*]").setAppName("PCATest")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val data = Array(
      Vectors.sparse(5, Seq((1, 1.0), (3, 7.0))),
      Vectors.dense(2.0, 0.0, 3.0, 4.0, 5.0),
      Vectors.dense(4.0, 0.0, 0.0, 6.0, 7.0)
    )
    val df = sqlContext.createDataFrame(data.map(Tuple1.apply)).toDF("features")
    val pca = new PCA()
      .setInputCol("features")
      .setOutputCol("pcaFeatures")
      .setK(3)
      .fit(df)
    val pcaDF = pca.transform(df)
    val result = pcaDF.select("pcaFeatures")
    result.show()
  }
}
