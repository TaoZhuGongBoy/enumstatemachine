package com.taozhugongboy.enumstatemachine.cola;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Cola状态机测试用例
 * @author taozhugongBoy
 */
public class ApplyStatusChoiceTest {

    static class Context {
        private String condition;

        public Context(String condition) {
            this.condition = condition;
        }

        public String getCondition() {
            return condition;
        }
    }

    @Test
    public void testChoice() {
        StateMachineBuilder<StateMachineTest.ApplyStates, StateMachineTest.ApplyEvents, Context> builder = StateMachineBuilderFactory.create();

        builder.externalTransition()
                .from(StateMachineTest.ApplyStates.APPLY_SUB)
                .to(StateMachineTest.ApplyStates.AUDIT_ING)
                .on(StateMachineTest.ApplyEvents.SUBMITING)
                .when(checkCondition1())
                .perform(doAction());


        StateMachine<StateMachineTest.ApplyStates, StateMachineTest.ApplyEvents, Context> stateMachine = builder.build("ChoiceConditionMachine");
        StateMachineTest.ApplyStates target1 = stateMachine.fireEvent(StateMachineTest.ApplyStates.APPLY_SUB, StateMachineTest.ApplyEvents.SUBMITING, new Context("pass"));
        Assert.assertEquals(StateMachineTest.ApplyStates.AUDIT_ING, target1);
//        StateMachineTest.States target2 = stateMachine.fireEvent(StateMachineTest.States.STATE1, StateMachineTest.Events.EVENT1, new StateMachineChoiceTest.Context("2"));
//        Assert.assertEquals(StateMachineTest.States.STATE2, target2);
//        StateMachineTest.States target3 = stateMachine.fireEvent(SxtateMachineTest.States.STATE1, StateMachineTest.Events.EVENT1, new StateMachineChoiceTest.Context("3"));
//        Assert.assertEquals(StateMachineTest.States.STATE3, target3);

    }


    private Condition<Context> checkCondition1() {
        return  (ctx) -> "pass".equals(ctx.getCondition());
    }

    private Action<StateMachineTest.ApplyStates, StateMachineTest.ApplyEvents, Context> doAction() {
        return (from, to, event, ctx)->{
            System.out.println("from:"+from+" to:"+to+" on:"+event+" condition:" + ctx.getCondition());
        };
    }
}
