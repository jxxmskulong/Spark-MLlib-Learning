package xingoo.scala

import scala.io.Source

/**
  * Created by xinghailong on 2017/6/26.
  */
object FileTest {
  def main(args: Array[String]) {
    val source = Source.fromFile("C:\\Users\\xinghailong\\Documents\\workspace\\my\\Spark-MLlib-Learning\\resouce\\lr_test","UTF-8")
//    printzln(source.mkString)
    source.getLines().foreach(println(_))

    import sys.process._
    "dir"!

  }
}
