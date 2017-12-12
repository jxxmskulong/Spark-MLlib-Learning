package xingoo.scala

/**
  * Created by xinghailong on 2017/6/28.
  */
sealed trait Test2{}
trait Test1{}

//case class A1 extends Test1{}
//case class B1 extends Test1{}
//case class C1 extends Test1{}
//
//case class A2 extends Test2{}
//case class B2 extends Test2{}
//case class C2 extends Test2{}

object TraitTest {
  def main(args: Array[String]) {
//    val x:Test1 = new B1()
//    x match {
//      case x @ A1() => println("A1")
//      case x @ B1() => println("B1")
//    }
  }
}

object TraitTest1 {
  def main(args: Array[String]) {
//    val x:Test2 = new B2()
/*    x match {
      case x @ A2() => println("A2")
      case x @ B2() => println("B2")
//      case x @ C2() => println("C2")
      /*
      Warning:(30, 5) match may not be exhaustive.
      It would fail on the following input: C2()
      x match {
      ^
*/
    }*/
  }
}

object TraitTest2 {
  def main(args: Array[String]) {
//    val x:Test2 = new B2()
//    x match {
//      case x @ A2() => println("A2")
//      case x @ B2() => println("B2")
//      case _ => println("other")
//    }
  }
}
