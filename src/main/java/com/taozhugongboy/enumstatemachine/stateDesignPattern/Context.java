package com.taozhugongboy.enumstatemachine.stateDesignPattern;

/**
 * @ClassName Context
 * @Description TODO
 * @Author zhangfengshi
 * @Date 2022/11/13
 * @Version 1.0.0
 */
public class Context {

    private SwitchState state;

    public Context(SwitchState state){
        this.state=state;
    }

    public void doAction(){
        state.handle();
    }
}
