package com.homework.dto.requestDTO;

import com.homework.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import java.io.Serializable;


@ApiModel(description = "获取员工列表的RequestDTO")
public class EmployeeListRequestDTO extends PageDTO implements Serializable {


    private static final long serialVersionUID = 8730179579528537353L;

    @ApiModelProperty(value = "员工姓名",example = "")
    private String name;

    @ApiModelProperty(value = "登陆账号",example = "")
    private String loginName;

    @ApiModelProperty(value = "状态 0代表禁用 1代表启用",example = "")
    private Integer status;

    @ApiModelProperty(value = "员工部门id",example = "")
    private Integer depId;

    @ApiModelProperty(value = "员工职位id",example = "")
    private Integer positionId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    @Override
    public String toString() {
        return "EmployeeListRequestDTO{" +
                "name='" + name + '\'' +
                ", loginName='" + loginName + '\'' +
                ", status=" + status +
                ", depId=" + depId +
                ", positionId=" + positionId +
                '}';
    }
}
