package com.homework.dto.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "有效职位列表,为下拉列表使用")
public class PositionActiveListResponseDTO implements Serializable {

    private static final long serialVersionUID = -2568677421069063399L;

    @ApiModelProperty(value = "职位id")
    private Integer positionId;

    @ApiModelProperty(value = "职位名称")
    private String positionName;

    @Override
    public String toString() {
        return "PositionActiveListResponseDTO{" +
                "positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                '}';
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
