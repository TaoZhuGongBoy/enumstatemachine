package com.taozhugongboy.enumstatemachine.simpleState;

/**
 * 枚举状态机
 * @author taozhugongBoy
 */
public enum State {
    SUBMIT_APPLY {
        @Override
        State transition(String checkcondition) {
            System.out.println("员工提交请假申请单,同步流转到部门经理审批 参数 = " + checkcondition);
            return Department_MANAGER_AUDIT;
        }
    },
    Department_MANAGER_AUDIT {
        @Override
        State transition(String checkcondition) {
            System.out.println("部门经理审批完成,同步跳转到HR进行审批 参数 = " + checkcondition);
            return HR;
        }
    },
    HR {
        @Override
        State transition(String checkcondition) {
            System.out.println("HR完成审批,流转到结束组件, 参数 = " + checkcondition);
            return FINAL;
        }
    },
    FINAL {
        @Override
        State transition(String checkcondition) {
            System.out.println("流程结束, 参数 = " + checkcondition);
            return this;
        }
    };

    abstract State transition(String checkcondition);
}
