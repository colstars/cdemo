package com.cdemo.demo.scala

import com.cdemo.demo.domain.Student

import scala.collection._
import scala.collection.mutable.ListBuffer

/**
 * @description:
 * @author: Administrator
 * @date: Created in 2020/3/3 12:43
 */
class TestScalaMethod {

  def testCommon(list: List[Student]): Unit = {
    val student1 = new Student("赵六", 5)
    println(s"+: => ${student1 +: list}") //在list头添加元素
    println(s":: => ${student1 :: list}") //在list头添加元素， +:同:: 区别？
  }

  def testForeach(list: List[Student]): Unit = {
    for (student <- list) {
      println(student)
    }
  }

  def testForeachWithOut(list: List[Student]): List[Student] = {
    /*for(student <- list){
      student.setMemo(student.getName + student.getAge)
    }*/
    list.foreach(s => s.memo = s.getName + s.getAge)
    list
  }

  def testList2Map(list: List[Student]): Map[String, Student] = {
    list.map(s => (s.getName -> s)).toMap
  }

  /**
   * @description:
   * Option也是一个容器，这个集合要么只包含一个包含在Some中的元素，要么不存在元素显示None
   *
   * Option 可能返回空的方法使用Option[X]作为返回类型,如果有值就返回Some[x](Some也是Option的子类)，否则返回None
   * None是一个object，是Option的子类型
   *
   * option get 获取返回对象， getOrElse 获取返回对象，如果对象为空可以指定返回对象
   * @date: 2020/3/8 13:27
   * @author: yanxingxing
   */
  def testListFilter2OneOption(list: List[Student]): Option[Student] = {
    //    list.filter(p => p.getName == "张三1").headOption
    list.filter(p => p.getName == "张三").find(p => p.getSex == "男")
  }

  /**
   * @description:
   * 如果返回对象没有数据，head方法报错 ： java.util.NoSuchElementException: head of empty list
   * @date: 2020/3/8 13:44
   * @author: yanxingxing
   */
  def testListFilter2One(list: List[Student]): Student = {
    /**
     * head() 方法取list的第一个对象
     */
    //    list.filter(p => p.getAge < 6).head

    /**
     * find 方法返回的是option[A],返回一个对象Some,返回空对象null,返回多个对象时Some()只返回第一个满足条件的对象
     */
    list.filter(p => p.getName != "张三").find(p => p.getSex == "男") match {
      case Some(student) => student
      case _ => null
    }
  }

  /**
   * @description:
   * Nil是一个空的List
   * @date: 2020/3/8 13:36
   * @author: yanxingxing
   */
  def testListFilter2List(list: List[Student]): List[Student] = {
    list.filter(p => p.getSex == "男")
  }

  def testMap(map: Map[String, Student]): Unit = {
    println(map.filterKeys(key => key == "张三"))
    println(map.filter(s => s._2.getSex == "男"))
  }

  /**
   * @description:
   * mutable.Map 可变长Map
   * Map to mutable.Map
   * @date: 2020/3/8 14:38
   * @author: yanxingxing
   */
  def testMutableMap(map: Map[String, Student]): Unit = {
    val mutableMap = immutable.Map(map.toSeq: _*)

//    println(mutableMap.getOrElseUpdate("颜六", new Student("颜六", 1)))
    println(map)
  }

}
