package com.taozhugongboy.enumstatemachine.colaStateMachine;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 状态机初始化
 *
 * @author taozhugongBoy
 */
@Configuration
public class StateMachineRegist {
    private static final Logger logger = LoggerFactory.getLogger(StateMachineRegist.class);

    private final String STATE_MACHINE_ID = "leaveStateMachineId";


    /**
     * 构建状态机实例
     */
    @Bean
    public StateMachine<ApplyStatusEnum, Event, LeaveContext> stateMachine() {

        StateMachineBuilder<ApplyStatusEnum, Event, LeaveContext> stateMachineBuilder = StateMachineBuilderFactory.create();
        //员工请假触发事件
        //因为没有源状态，初始化时只是同一个状态流转;所以用内部流转
        stateMachineBuilder.internalTransition().within(ApplyStatusEnum.LEAVE_SUBMIT).on(Event.EMPLOYEE_SUBMIT).perform(doAction());
        //部门主管审批触发事件（依赖上一个状态：LEAVE_SUBMIT）
        stateMachineBuilder.externalTransition().from(ApplyStatusEnum.LEAVE_SUBMIT).to(ApplyStatusEnum.LEADE_AUDIT_PASS).on(Event.DIRECTLEADER_AUDIT).when(checkIfPass()).perform(doAction());
        stateMachineBuilder.externalTransition().from(ApplyStatusEnum.LEAVE_SUBMIT).to(ApplyStatusEnum.LEADE_AUDIT_REFUSE).on(Event.DIRECTLEADER_AUDIT).when(checkIfNotPass()).perform(doAction());
        //hr事件触发(依赖上一个状态:LEADE_AUDIT_PASS)
        stateMachineBuilder.externalTransition().from(ApplyStatusEnum.LEADE_AUDIT_PASS).to(ApplyStatusEnum.HR_PASS).on(Event.HR_AUDIT).when(checkIfPass()).perform(doAction());
        stateMachineBuilder.externalTransition().from(ApplyStatusEnum.LEADE_AUDIT_PASS).to(ApplyStatusEnum.HR_REFUSE).on(Event.HR_AUDIT).when(checkIfNotPass()).perform(doAction());

        return stateMachineBuilder.build(STATE_MACHINE_ID);


    }

    private Condition<LeaveContext> checkIfPass() {
        return (ctx) -> ctx.getIdea().equals(0);
    }

    private Condition<LeaveContext> checkIfNotPass() {
        return (ctx) -> ctx.getIdea().equals(1);
    }

    /**
     * 事件触发后，匹配成功对应的条件后，就会执行具体动作<br>
     * 可以自定义完成具体业务逻辑...
     * @return
     */
    private Action<ApplyStatusEnum, Event, LeaveContext> doAction() {
        return (from, to, event, ctx) -> {
            logger.info("from:" + from + " to:" + to + " on:" + event + " condition:" + ctx);
        };
    }


    }
