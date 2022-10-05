package com.cdemo.learnstatemachine.core;

/**
 * @description: S State
 * E Event
 * H Handler
 * @author: star
 * @time: 2021/7/24 16:00
 */
public class StateMachine<S, E, H extends Handler> {

    private final StateMachineConfig<S, E, H> stateMachineConfig;

    public StateMachine(StateMachineConfig<S, E, H> stateMachineConfig) {
        this.stateMachineConfig = stateMachineConfig;
    }

    //当前状态
    private final static String CURRENT_STATE = "CURRENT_STATE";

    /**
     * 触发器
     *
     * @author: col_star
     * @time: 2021/7/24 17:41
     */
    public void fire(E event, Context<S, E> context) {
        final S state = context.getState(CURRENT_STATE);


    }
}
