package com.taozhugongboy.enumstatemachine.stateDesignPattern;

/**
 * 关灯实现类
 * @author taozhugongBoy
 */
public class TurnOffAction implements SwitchState{
    @Override
    public void handle() {
        System.out.println("关灯");
    }
}
