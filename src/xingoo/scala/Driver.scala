package xingoo.scala
import xingoo.scala.TrafficLightColor.TrafficLightColor
import xingoo.scala.TrafficLightColor._
import xingoo.scala.Margin._
/**
  * Created by xinghailong on 2017/6/23.
  */
object Driver extends App {
    println(BOTTOM, BOTTOM.id)

    def doWhat(color: TrafficLightColor) = {
      if (color == Red) "stop"
      else if (color == Yellow) "hurry up" else "go"
    }

    //使用match匹配
    def doWhat2(color: TrafficLightColor) = color match {
      case Red    => "stop"
      case Yellow => "hurry up"
      case _      => "go"
    }

    // load Red
    val red = TrafficLightColor(0) // Calls Enumeration.apply
    println(red, red.id)
    println(doWhat(red))
    println(doWhat2(TrafficLightColor.Yellow))

    //打印出所有枚举
    Margin.values.foreach { v => println(v,v.id)}
}
