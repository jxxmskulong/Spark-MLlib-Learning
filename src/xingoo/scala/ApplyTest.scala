package xingoo.scala

/**
  * Created by xinghailong on 2017/7/14.
  */
case class Person(name:String,age:Int){
  def apply(name:String,age:Int):Person = {
    new Person(name,age)
  }

  def unapply(a: Person): Option[String] = {
    a match {
      case a if(a.age > 18) => {
        Some(a.name)
      }
      case _ => None
    }
  }
}

object ApplyTest {
  def main(args: Array[String]) {
    val p1 = Person("张三",15)
    val p2 = Person("李四",20)
    val p3 = Person("王五",17)

    val list = Array(p1,p2,p3)

    list.foreach( x => {
      x match {
        case Person(a,b) => println(a)
        case _ => println("no match")
      }
    })

    println(Person.unapply(p1))
  }
}
