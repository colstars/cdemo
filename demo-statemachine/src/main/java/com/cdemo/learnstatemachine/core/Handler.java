package com.cdemo.learnstatemachine.core;

public interface Handler <C extends Context>{

    void handler(C context, StateMachine stateMachine);
}
