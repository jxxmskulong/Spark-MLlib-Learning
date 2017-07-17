package xingoo.scala

/**
  * Created by xinghailong on 2017/6/27.
  */
object CaseTest {
  def main(args: Array[String]) {
    test("a")
    test("b")
    test("c")
    test("ddd")
  }
  def test(a:String):Unit = {
    a match {
      case "a" => println("aaa")
      case "b" => println("bbb")
      case "c" => println("ccc")
      case _ =>
    }
  }
}
