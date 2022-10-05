package com.cdemo.learnstatemachine.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: star
 * @time: 2021/7/24 16:07
 */
public class StateMachineConfig<S, E, H> {
    private S tempState;
    private E tempEvent;
    private H tempHandle;
    private S tempNextState;

    private final Map<S, StateConfiguration<S, E ,H>> stateConfigurationMap = new HashMap<>();

    public StateMachineConfig from (S s){
        this.tempState = s;
        return this;
    }

    public StateMachineConfig premit (E e){
        this.tempEvent = e;
        return this;
    }

    public StateMachineConfig handle (H h){
        this.tempHandle = h;
        return this;
    }

    public StateMachineConfig to (S s){
        this.tempNextState = s;
        return this;
    }

    public void build(){
        if(stateConfigurationMap.get(tempState) == null){
            final StateConfiguration<S, E, H> stateConfiguration = new StateConfiguration<>(tempState);
            stateConfigurationMap.put(tempState, stateConfiguration);
        }

        this.tempState = null;
        this.tempEvent = null;
        this.tempHandle = null;
        this.tempNextState = null;
    }

    public H getHandle(){
        return null;
    }


    private class StateConfiguration<S, E ,H>{
        private S s;

        private Map<E, H> eventHandleMap;
        private Map<E, S> nextStateMap;

        public StateConfiguration(S s) {
            this.s = s;
            this.eventHandleMap = new HashMap<>();
            this.nextStateMap = new HashMap<>();
        }

        public void addEventHandle(E e, H h){
            eventHandleMap.put(e, h);
        }

        public void addNextState(E e, S s){
            nextStateMap.put(e, s);
        }
    }

}
