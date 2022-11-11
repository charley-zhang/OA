package com.homework.enums;


import io.swagger.models.auth.In;

/**
 * 员工状态枚举类
 */
public enum StatusEnum {

    ONJOB(1,"在职"),RESIGN(0,"离职");

    private  Integer status;

    private String statusName;

    StatusEnum(Integer status,String statusName){
        this.status = status;
        this.statusName = statusName;
    }

    public Integer getStatus() {
        return status;
    }

    public String getStatusName() {
        return statusName;
    }

    public static StatusEnum statusOf(Integer status){
        for (StatusEnum statusEnum : values()) {
            if (statusEnum.getStatus()==status){
                return statusEnum;
            }
        }
        return null;
    }

}
