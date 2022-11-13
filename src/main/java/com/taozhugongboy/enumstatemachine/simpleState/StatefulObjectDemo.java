package com.taozhugongboy.enumstatemachine.simpleState;

/**
 * 枚举状态机DEMO
 * @author taozhugongBoy
 */
public class StatefulObjectDemo {
    private  State state;

    public StatefulObjectDemo() {
        state = State.SUBMIT_APPLY;
    }

    public void performRequest(String checkCondition) {
        state = state.transition(checkCondition);
    }

    public static void main(String[] args) {
      StatefulObjectDemo theObject = new StatefulObjectDemo();
        theObject.performRequest("arg1");
        theObject.performRequest("arg2");
        theObject.performRequest("arg3");
        theObject.performRequest("arg4");

    }
}
