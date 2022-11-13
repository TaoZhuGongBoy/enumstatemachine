package com.taozhugongboy.enumstatemachine.stateDesignPattern;

/**
 * 开关实现类
 * @author taozhugongBoy
 */
public class TurnOnAction implements SwitchState{

    @Override
    public void handle() {
        System.out.println("开灯");
    }
}
