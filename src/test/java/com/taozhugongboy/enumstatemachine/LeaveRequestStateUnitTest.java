package com.taozhugongboy.enumstatemachine;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * 状态机测试类
 *
 * @author taozhugongBoy
 */
public class LeaveRequestStateUnitTest {

    @DisplayName("申请状态机")
    @Test
    public void givenLeaveRequest_thenStateIsChanged() {
        LeaveRequestState leaveRequestState = LeaveRequestState.Submitted;
        //员工提交请假申请单
        Integer state = leaveRequestState.setApplyStatus(OpinionsEnum.PASS);
        assertEquals(Objects.toString(state),"1");
        //主管同意
        leaveRequestState = LeaveRequestState.Escalated;
        state = leaveRequestState.setApplyStatus(OpinionsEnum.PASS);
        assertEquals(Objects.toString(state), "2");
        //HR同意
        leaveRequestState = LeaveRequestState.Approved;
        state = leaveRequestState.setApplyStatus(OpinionsEnum.PASS);
        assertEquals(Objects.toString(state), "3");
    }
}
