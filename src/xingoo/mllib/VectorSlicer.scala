package xingoo.mllib

import org.apache.spark.ml.attribute.{Attribute, AttributeGroup, NumericAttribute}
import org.apache.spark.ml.feature.VectorSlicer
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.StructType
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by xinghailong on 2017/7/11.
  */
object VectorSlicer {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("MovieLensALS-Test").setMaster("local[2]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    var sqlContext = new SQLContext(sc)

    val data = Array(Row(Vectors.dense(-2.0, 2.3, 0.0,1.0,2.0)))

    val defaultAttr = NumericAttribute.defaultAttr
    val attrs = Array("f1", "f2", "f3","f4","f5").map(defaultAttr.withName)
    val attrGroup = new AttributeGroup("userFeatures", attrs.asInstanceOf[Array[Attribute]])

    val dataRDD = sc.parallelize(data)
    val dataset = sqlContext.createDataFrame(dataRDD, StructType(Array(attrGroup.toStructField())))

    val slicer = new VectorSlicer().setInputCol("userFeatures").setOutputCol("features")

    slicer.setNames(Array("f8"))
//    slicer.setIndices(Array(1, 2, 4))
//    slicer.setNames(Array("f2", "f3"))

    val output = slicer.transform(dataset)
    output.show()
    println(output.select("userFeatures", "features").first())
  }
}
