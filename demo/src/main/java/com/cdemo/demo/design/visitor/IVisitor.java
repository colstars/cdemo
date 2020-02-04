package com.cdemo.demo.design.visitor;

import com.cdemo.demo.design.visitor.model.Student;
import com.cdemo.demo.design.visitor.model.Teacher;

/**
 * @description: 
 * @create: 2019-12-06 14:56:55
 * @author: Mr.Yanxingxing 
 */
public interface IVisitor {
    void visit(Teacher teacher);
    void visit(Student student);
}
