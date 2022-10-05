package com.cdemo.learnstatemachine.core;

/**
 * 上下文
 * @author: col_star
 * @time: 2021/7/24 15:44
 */    
public interface Context <S, E>{
    S getState(String s);

}
