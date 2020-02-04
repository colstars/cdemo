package com.cdemo.demo.design.visitor.model;

import com.cdemo.demo.design.visitor.IElement;
import com.cdemo.demo.design.visitor.IVisitor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher implements IElement {
    private String name;
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
