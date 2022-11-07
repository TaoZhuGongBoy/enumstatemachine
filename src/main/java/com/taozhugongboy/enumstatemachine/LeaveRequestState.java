package com.taozhugongboy.enumstatemachine;

/**
 * 枚举状态机
 *
 * @author taozhugongBoy
 */
public enum LeaveRequestState {

    /**
     * 员工提交申请<br>
     *
     * @param opinions 操作意见枚举
     * @return 1-已申请 2-审核中 3-审核成功 4-审核失败
     */
    Submitted {
        @Override
        public Integer setApplyStatus(OpinionsEnum opinionsEnum) {
            System.out.println("员工提交请假申请单，申请状态已申请...");
            return 1;
        }
    },

    /**
     * 直属主管审批<br>
     *
     * @param opinions 操作意见枚举
     * @return 1-已申请 2-审核中 3-审核成功 4-审核失败
     */
    Escalated {
        @Override
        public Integer setApplyStatus(OpinionsEnum opinionsEnum) {
            switch (opinionsEnum) {
                case PASS:
                    System.out.println("直属主管审批通过,申请状态审核中...");
                    return 2;
                case NOT_PASS:
                    System.out.println("直属主管审批失败,申请状态审核失败...");
                    return 4;
            }
            return null;
        }

    },
    /**
     * HR审批<br>
     *
     * @param opinions 操作意见枚举
     * @return 1-已申请 2-审核中 3-审核成功 4-审核失败
     */
    Approved {
        @Override
        public Integer setApplyStatus(OpinionsEnum opinionsEnum) {
            switch (opinionsEnum) {
                case PASS:
                    System.out.println("HR审批通过,申请状态审核成功...");
                    return 3;
                case NOT_PASS:
                    System.out.println("HR审批退回,申请状态审核失败...");
                    return 4;
            }
            return null;
        }
    };

    /**
     * 申请状态设置
     *
     * @param opinionsEnum 操作意见枚举
     * @return 1-已申请 2-审核中 3-审核成功 4-审核失败
     */
    public abstract Integer setApplyStatus(OpinionsEnum opinionsEnum);




}
