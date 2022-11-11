package com.homework.dto.requestDTO;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "切换单个部门RequestDTO")
public class ToggleEmployeeRequestDTO implements Serializable {


    private static final long serialVersionUID = -3682464900559526325L;

    @ApiModelProperty(value = "员工id",example = "1")
    private Integer emId;

    @ApiModelProperty(value = "目前的状态(0代表离职,1表示在职)",example = "1")
    private Integer status;

    public Integer getEmId() {
        return emId;
    }

    public void setEmId(Integer emId) {
        this.emId = emId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
