package com.cdemo.demo.scala.data

import com.cdemo.demo.domain.Student

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
 * @description:
 * @author: Administrator
 * @date: Created in 2020/3/8 12:23
 */
class TestScalaData {
  def generateList(): ListBuffer[Student] = {
    val list = ListBuffer[Student]()
    list += new Student("张三", 10, "男")
    list += new Student("李四", 3, "女")
    list += new Student("王五", 8, "男")
  }

  def generateArray(): ArrayBuffer[Student] = {
    val list = ArrayBuffer[Student]()
    list += new Student("张三", 10)
    list += new Student("李四", 3)
    list += new Student("王五", 8)
  }


}
