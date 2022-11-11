package com.homework.dto.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "有效部门列表,为下拉列表使用")
public class DepartmentActiveListResponseDTO implements Serializable {

    private static final long serialVersionUID = 4044866393796489714L;

    @ApiModelProperty(value = "部门id")
    private Integer depId;

    @ApiModelProperty(value = "部门名称")
    private String name;

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DepartmentActiveListResponseDTO{" +
                "depId=" + depId +
                ", name='" + name + '\'' +
                '}';
    }
}
