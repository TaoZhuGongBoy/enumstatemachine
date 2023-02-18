package com.taozhugongboy.enumstatemachine.stateDesignPattern;

/**
 * @ClassName Context
 * @Description TODO
 * @Author taozhugongBoy
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
