package com.homework.dto.requestDTO;

import com.homework.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import java.io.Serializable;

@ApiModel(description = "获取部门列表RequestDTO")
public class DepartmentListRequestDTO extends PageDTO implements Serializable{

    private static final long serialVersionUID = -8920106711693902194L;

    @ApiModelProperty(value = "部门名称关键字",example = "")
    private String name;

    @ApiModelProperty(value = "部门地址关键字",example = "")
    private String address;

    @ApiModelProperty(value = "状态",example = "")
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DepartmentListRequestDTO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}
