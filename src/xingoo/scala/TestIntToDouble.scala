package xingoo.scala


/**
  * Created by xinghailong on 2017/7/6.
  */
object TestIntToDouble {
  def main(args: Array[String]) {
    val i = 0
    val j = 1
    println(i)
    println(j)
    println(i.toDouble)
    println(j.toDouble)

    val list = List("a","b","c")

    for(i <- 0 until list.length){
      println(i)
      println(list(i),i.toDouble)
    }
  }

}
