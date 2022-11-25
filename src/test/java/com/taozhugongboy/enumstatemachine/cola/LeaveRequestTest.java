package com.taozhugongboy.enumstatemachine.cola;

import com.alibaba.cola.statemachine.State;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.StateMachineFactory;
import com.taozhugongboy.enumstatemachine.SpringApplication;
import com.taozhugongboy.enumstatemachine.colaStateMachine.ApplyStatusEnum;
import com.taozhugongboy.enumstatemachine.colaStateMachine.Event;
import com.taozhugongboy.enumstatemachine.colaStateMachine.LeaveContext;
import oracle.jvm.hotspot.jfr.ThreadStates;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author taozhugongBoy
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringApplication.class)
public class LeaveRequestTest {

    @Resource(name = "stateMachine")
    private StateMachine stateMachineInstance;


    @DisplayName("员工提交请假申请单")
    @Test
    public void employSubmitRequest(){

        StateMachine<ApplyStatusEnum, Event, LeaveContext> stateMachine = StateMachineFactory.get("leaveStateMachineId");
        LeaveContext context = new LeaveContext();

        ApplyStatusEnum state=stateMachine.fireEvent(ApplyStatusEnum.LEAVE_SUBMIT, Event.EMPLOYEE_SUBMIT,context);

        Assert.assertEquals(ApplyStatusEnum.LEAVE_SUBMIT.getCode(),state.getCode());

    }

    @DisplayName("部门主管审批通过")
    @Test
    public void leaderAuditPass(){

        StateMachine<ApplyStatusEnum, Event, LeaveContext> stateMachine = StateMachineFactory.get("leaveStateMachineId");
        LeaveContext context = new LeaveContext();
        //主管审批通过
        context.setIdea(0);
        ApplyStatusEnum state=stateMachine.fireEvent(ApplyStatusEnum.LEAVE_SUBMIT, Event.DIRECTLEADER_AUDIT,context);
        Assert.assertEquals(ApplyStatusEnum.LEADE_AUDIT_PASS.getCode(),state.getCode());
    }

    @DisplayName("部门主管审批不通过")
    @Test
    public void leaderAuditNotPass(){

        StateMachine<ApplyStatusEnum, Event, LeaveContext> stateMachine = StateMachineFactory.get("leaveStateMachineId");
        LeaveContext context = new LeaveContext();
        //主管审批不通过
        context.setIdea(1);
        ApplyStatusEnum state=stateMachine.fireEvent(ApplyStatusEnum.LEAVE_SUBMIT, Event.DIRECTLEADER_AUDIT,context);
        Assert.assertEquals(ApplyStatusEnum.LEADE_AUDIT_REFUSE.getCode(),state.getCode());
    }


    @DisplayName("HR审批通过")
    @Test
    public void hrAuditPass(){

        StateMachine<ApplyStatusEnum, Event, LeaveContext> stateMachine = StateMachineFactory.get("leaveStateMachineId");
        LeaveContext context = new LeaveContext();
        //HR通过
        context.setIdea(0);
        ApplyStatusEnum state=stateMachine.fireEvent(ApplyStatusEnum.LEADE_AUDIT_PASS, Event.HR_AUDIT,context);
        Assert.assertEquals(ApplyStatusEnum.HR_PASS.getCode(),state.getCode());
    }

    @DisplayName("HR审批不通过")
    @Test
    public void hrAuditNotPass(){

        StateMachine<ApplyStatusEnum, Event, LeaveContext> stateMachine = StateMachineFactory.get("leaveStateMachineId");
        LeaveContext context = new LeaveContext();
        //HR审批不通过
        context.setIdea(1);
        ApplyStatusEnum state=stateMachine.fireEvent(ApplyStatusEnum.LEADE_AUDIT_PASS, Event.HR_AUDIT,context);
        Assert.assertEquals(ApplyStatusEnum.HR_REFUSE.getCode(),state.getCode());
    }




}
