package com.homework.dto.requestDTO;

import com.homework.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "获取部门列表RequestDTO")
public class PositionListRequestDTO extends PageDTO implements Serializable{


    private static final long serialVersionUID = 7867746007388352488L;

    @ApiModelProperty(value = "部门名称关键字",example = "")
    private String positionName;


    @ApiModelProperty(value = "状态",example = "")
    private Integer status;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PositionListRequestDTO{" +
                "name='" + positionName + '\'' +
                ", status=" + status +
                '}';
    }
}
