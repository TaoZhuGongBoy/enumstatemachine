package com.taozhugongboy.enumstatemachine.enumState;

/**
 * 枚举状态机<br>
 * 请假案例
 * @author taozhugongBoy
 */
public enum LeaveRequestStateEnum {
    Submitted {
        @Override
        public LeaveRequestStateEnum nextState() {
            return Escalated;
        }

        @Override
        public String responsiblePerson() {
            return "Employee";
        }
    },
    Escalated {
        @Override
        public LeaveRequestStateEnum nextState() {
            return Approved;
        }

        @Override
        public String responsiblePerson() {
            return "Team Leader";
        }
    },
    Approved {
        @Override
        public LeaveRequestStateEnum nextState() {
            return this;
        }

        @Override
        public String responsiblePerson() {
            return "Department Manager";
        }
    };
    //下一个状态
    public abstract LeaveRequestStateEnum nextState();
    //责任人
    public abstract String responsiblePerson();
}
