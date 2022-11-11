package com.homework.service;

import com.homework.dto.ToggleDepartmentRequestDTO;
import com.homework.dto.requestDTO.DepartmentListRequestDTO;
import com.homework.entity.Department;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门表(Department)表服务接口
 *
 * @author makejava
 * @since 2021-05-17 21:30:13
 */
public interface DepartmentService {

    /**
     * 通过ID查询单条数据
     *
     * @param depId 主键
     * @return 实例对象
     */
    Department queryById(Integer depId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Department> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    Department insert(Department department);

    /**
     * 修改数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    Department update(Department department);

    /**
     * 通过主键删除数据
     *
     * @param depId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer depId);

    /**
     * 获取部门信息列表
     * @return
     */
    Map<String, Object> queryByPage(DepartmentListRequestDTO departmentListrequestDTO);

    void toggleStatus(ToggleDepartmentRequestDTO toggleDepartmentRequestDTO);

    Map<String, Object> queryActiveList();

}
