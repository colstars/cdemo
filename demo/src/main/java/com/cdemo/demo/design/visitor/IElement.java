package com.cdemo.demo.design.visitor;

/**
 * @description: 创建抽象元素，
 * @create: 2019-12-06 13:44:50
 * @author: Mr.Yanxingxing
 */
public interface IElement {

    void accept(IVisitor visitor);
}
