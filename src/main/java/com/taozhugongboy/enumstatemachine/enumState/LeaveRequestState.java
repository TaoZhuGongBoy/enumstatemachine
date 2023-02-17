package com.taozhugongboy.enumstatemachine.enumState;

/**
 * 枚举状态机
 * @author taozhugongBoy
 */
public enum LeaveRequestState {
    Submitted {
        @Override
        public LeaveRequestState nextState() {
            return Escalated;
        }

        @Override
        public String responsiblePerson() {
            return "Employee";
        }
    },
    Escalated {
        @Override
        public LeaveRequestState nextState() {
            return Approved;
        }

        @Override
        public String responsiblePerson() {
            return "Team Leader";
        }
    },
    Approved {
        @Override
        public LeaveRequestState nextState() {
            return this;
        }

        @Override
        public String responsiblePerson() {
            return "Department Manager";
        }
    };

    public abstract LeaveRequestState nextState();
    public abstract String responsiblePerson();
}
