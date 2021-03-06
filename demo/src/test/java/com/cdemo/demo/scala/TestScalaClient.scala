package com.cdemo.demo.scala

import com.cdemo.demo.domain.Student
import com.cdemo.demo.scala.data.TestScalaData
import org.springframework.util.StringUtils

/**
 * @description:
 * @author: Administrator
 * @date: Created in 2020/3/5 9:17
 */
object TestScalaClient extends TestScalaData {
  val testScala: TestScalaMethod = new TestScalaMethod()

  def main(args: Array[String]) {
    val list = generateList().toList;

//    testScala.testForeach(list)
//    println(testScala.testForeachWithOut(list))
//    println(testScala.testList2Map(list))
//    println(testScala.testListFilter2One(list))
//    println(testScala.testListFilter2OneOption(list).getOrElse(new Student("haha", 1)))
    println(testScala.testListFilter2OneOption(list))
//    println(testScala.testListFilter2List(list))
//      testScala.testMap(testScala.testList2Map(list))
//      testScala.testMutableMap(testScala.testList2Map(list))

    println(testScala.testList2(list))




  }

}
