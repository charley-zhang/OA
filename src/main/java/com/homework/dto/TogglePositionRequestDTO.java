package com.homework.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "切换单个职位状态RequestDTO")
public class TogglePositionRequestDTO implements Serializable {

    private static final long serialVersionUID = -6755946852890597528L;

    @ApiModelProperty(value = "职位id",example = "1")
    private Integer positionId;
    @ApiModelProperty(value = "当前状态",example = "1")
    private Integer status;

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
