package com.cdemo.demo.design.visitor.model;

import com.cdemo.demo.design.visitor.IVisitor;

/**
 * @description: 访问者B ，针对名字做掩码处理
 * @create: 2019-12-06 15:01:32
 * @author: Mr.Yanxingxing
 */
public class VistorB implements IVisitor {
    public void visit(Teacher teacher) {
        System.out.println(teacher.getName().substring(0,1).concat("**"));
    }

    public void visit(Student student) {
        System.out.println(student.getName().substring(0,1).concat("**"));
    }
}
