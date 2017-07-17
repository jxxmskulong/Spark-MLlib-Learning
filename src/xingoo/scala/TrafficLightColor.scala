package xingoo.scala


/**
  * Created by xinghailong on 2017/6/23.
  */
object TrafficLightColor extends Enumeration{
  type TrafficLightColor = Value
  val Red = Value(0, "Stop")
  val Yellow = Value(10)
  val Green = Value("Go")
}
