package com.cdemo.demo.design.visitor.model;

import com.cdemo.demo.design.visitor.IVisitor;

/**
 * @description: 访问者B ，针对名字做掩码处理
 * @create: 2019-12-06 15:01:32
 * @author: Mr.Yanxingxing
 */
public class VistorC implements IVisitor {
    public void visit(Teacher teacher) {

        teacher.setName("我是老师：" + teacher.getName());
    }

    public void visit(Student student) {

        student.setName("我是学生："+ student.getName());
    }
}
