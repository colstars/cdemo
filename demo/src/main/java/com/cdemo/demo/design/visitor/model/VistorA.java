package com.cdemo.demo.design.visitor.model;

import com.cdemo.demo.design.visitor.IVisitor;

/**
 * @description: 访问者A ，输出名字
 * @create: 2019-12-06 15:01:32
 * @author: Mr.Yanxingxing 
 */
public class VistorA implements IVisitor {
    public void visit(Teacher teacher) {
        System.out.println(teacher.getName());
    }

    public void visit(Student student) {
        System.out.println(student.getName());
    }
}
