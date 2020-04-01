package com.cdemo.demo.scala.`enum`

/**
 * @description:
 * @author: Administrator
 * @date: Created in 2020/3/8 15:22
 */
class SexEnum extends Enumeration {
  type SexEnum = Value
  val BOY = Value(1, "男")
  val GIRL = Value(0, "女")
}
