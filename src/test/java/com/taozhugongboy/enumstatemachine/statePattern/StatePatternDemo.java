package com.taozhugongboy.enumstatemachine.statePattern;

import com.taozhugongboy.enumstatemachine.stateDesignPattern.Context;
import com.taozhugongboy.enumstatemachine.stateDesignPattern.TurnOffAction;
import com.taozhugongboy.enumstatemachine.stateDesignPattern.TurnOnAction;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * 状态模式测试用例
 *
 * @author taozhugongBoy
 */
public class StatePatternDemo {

    @DisplayName("状态模式测试用例-开灯")
    @Test
    public void turnOn() {
        Context context = new Context(new TurnOnAction());
        context.doAction();
    }

    @DisplayName("状态模式测试用例-关灯")
    @Test
    public void turnOff() {
        Context context = new Context(new TurnOffAction());
        context.doAction();
    }
}
