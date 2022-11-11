package com.homework.dto.responseDTO;

import com.homework.dto.EmployeeDTO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class EmployeeResponseDTO implements Serializable {


    private static final long serialVersionUID = -7256931845404569314L;

    @ApiModelProperty(value = "员工信息")
    private EmployeeDTO employeeDTO;
    @ApiModelProperty(value = "部门下拉列表信息")
    private List<DepartmentActiveListResponseDTO> departmentList;
    @ApiModelProperty(value = "职位下拉列表信息信息")
    private List<PositionActiveListResponseDTO> positionList;

    public List<DepartmentActiveListResponseDTO> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<DepartmentActiveListResponseDTO> departmentList) {
        this.departmentList = departmentList;
    }

    public List<PositionActiveListResponseDTO> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<PositionActiveListResponseDTO> positionList) {
        this.positionList = positionList;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    @Override
    public String toString() {
        return "EmployeeListResponseDTO{" +
                "employeeDTO=" + employeeDTO +
                '}';
    }
}
