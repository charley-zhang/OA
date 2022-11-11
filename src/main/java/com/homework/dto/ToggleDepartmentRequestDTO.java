package com.homework.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.io.Serializable;

@ApiModel(value = "切换但各部门状态RequestDTO")
public class ToggleDepartmentRequestDTO implements Serializable {

    private static final long serialVersionUID = -6755946852890597528L;

    @ApiModelProperty(value = "部门id",example = "1")
    private Integer depId;
    @ApiModelProperty(value = "当前状态",example = "1")
    private Integer status;

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
