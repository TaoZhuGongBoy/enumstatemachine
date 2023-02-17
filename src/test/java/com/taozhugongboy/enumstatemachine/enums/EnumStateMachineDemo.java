package com.taozhugongboy.enumstatemachine.enums;

import com.taozhugongboy.enumstatemachine.enumState.LeaveRequestState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 枚举状态机
 * @author taozhugongBoy
 */
public class EnumStateMachineDemo {

    LeaveRequestState state = LeaveRequestState.Submitted;

    @Test
    public void execute(){

        state = state.nextState();
        assertEquals(LeaveRequestState.Escalated, state);

        state = state.nextState();
        assertEquals(LeaveRequestState.Approved, state);

        state = state.nextState();
        assertEquals(LeaveRequestState.Approved, state);
    }
}
