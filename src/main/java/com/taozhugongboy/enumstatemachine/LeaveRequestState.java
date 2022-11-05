package com.taozhugongboy.enumstatemachine;

/**
 * 枚举状态机
 * @author taozhugongBoy
 */
public enum LeaveRequestState {

    /**
     * 初始化申请单<br/>
     * 员工个人
     */
    Submitted {
        @Override
        public LeaveRequestState nextState() {
            System.out.println("开始提交申请单，交由主管进行审批.");
            return Escalated;
        }

        @Override
        public String responsiblePerson() {
            return "Employee";
        }
    },
    /**
     * 进行时<br>
     * 直接主管
     */
    Escalated {
        @Override
        public LeaveRequestState nextState() {
            System.out.println("主管审批完成，下一环由部门经理审批...");
            return Approved;
        }

        @Override
        public String responsiblePerson() {
            return "Team Leader";
        }
    },
    /**
     * 最后审批<br>
     * 角色：部门经理
     */
    Approved {
        @Override
        public LeaveRequestState nextState() {
            System.out.println("部门经理审批通过！");
            return this;
        }

        @Override
        public String responsiblePerson() {
            return "Department Manager";
        }
    };

    /**
     * 操作人
     * @return
     */
    public abstract String responsiblePerson();

    /**
     * 下一个流转状态
     * @return
     */
    public abstract LeaveRequestState nextState();

}
