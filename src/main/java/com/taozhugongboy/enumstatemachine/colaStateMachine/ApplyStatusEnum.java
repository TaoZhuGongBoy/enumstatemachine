package com.taozhugongboy.enumstatemachine.colaStateMachine;



/**
 * @author taozhugongBoy
 */
public enum ApplyStatusEnum {


    LEAVE_SUBMIT(1,"已申请"),LEADE_AUDIT_PASS(2,"直属领导审批通过"),LEADE_AUDIT_REFUSE(3,"直属领导审批失败"),HR_PASS(4,"审批通过"),HR_REFUSE(5,"HR拒绝"),END(6,"END");


    private Integer code;
    private String desc;

    ApplyStatusEnum(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
