package com.homework.service;

import com.homework.dto.EmployeeDTO;
import com.homework.dto.ToggleDepartmentRequestDTO;
import com.homework.dto.requestDTO.DepartmentListRequestDTO;
import com.homework.dto.requestDTO.EmployeeListRequestDTO;
import com.homework.dto.requestDTO.ToggleEmployeeRequestDTO;
import com.homework.dto.responseDTO.EmployeeResponseDTO;
import com.homework.entity.Employee;

import java.util.List;
import java.util.Map;

/**
 * 员工表(Employee)表服务接口
 *
 * @author makejava
 * @since 2021-05-17 21:11:29
 */
public interface EmployeeService {

    /**
     * 通过ID查询单条数据
     *
     * @param emId 主键
     * @return 实例对象
     */
    EmployeeResponseDTO queryById(Integer emId) throws Exception;

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Employee> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    Employee insert(Employee employee);

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    Integer update(Employee employee);

    /**
     * 通过主键删除数据
     *
     * @param emId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer emId);

    EmployeeDTO GetEmIfnoByUsernameAndPassword(String username, String password);

    //************************************************
    /**
     * 获取员工信息列表
     * @return
     */
    Map<String, Object> queryByPage(EmployeeListRequestDTO employeeListrequestDTO);

    void toggleStatus(ToggleEmployeeRequestDTO toggleemployeeRequestDTO);
}

