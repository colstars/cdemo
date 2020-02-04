package com.cdemo.demo.design.visitor;

import com.cdemo.demo.design.visitor.model.*;

public class Client {

    public static void main(String[] args) {
        Teacher teacher = new Teacher("孙老师");
        Student studentA = new Student("张三");
        Student studentB = new Student("李四");

        IVisitor visitorA = new VistorA();
        teacher.accept(visitorA);
        studentA.accept(visitorA);
        studentB.accept(visitorA);

        System.out.println("----------------");


        IVisitor visitorB = new VistorB();
        teacher.accept(visitorB);
        studentA.accept(visitorB);
        studentB.accept(visitorB);

        System.out.println("----------------");

        IVisitor visitorC = new VistorC();
        teacher.accept(visitorC);
        studentA.accept(visitorC);
        studentB.accept(visitorC);
        System.out.println(teacher.getName());
        System.out.println(studentA.getName());
        System.out.println(studentA.getName());
    }
}
