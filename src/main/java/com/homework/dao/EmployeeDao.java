package com.homework.dao;

import com.homework.dto.EmployeeDTO;
import com.homework.dto.requestDTO.EmployeeListRequestDTO;
import com.homework.entity.Department;
import com.homework.entity.Employee;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 员工表(Employee)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-17 21:11:29
 */
public interface EmployeeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param emId 主键
     * @return 实例对象
     */
    EmployeeDTO queryById(Integer emId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Employee> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param employee 实例对象
     * @return 对象列表
     */
    List<Employee> queryAll(Employee employee);

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 影响行数
     */
    int insert(Employee employee);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Employee> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Employee> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Employee> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Employee> entities);

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 影响行数
     */
    int update(Employee employee);

    /**
     * 通过主键删除数据
     *
     * @param emId 主键
     * @return 影响行数
     */
    int deleteById(Integer emId);

    EmployeeDTO queryEmIfnoByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    List<EmployeeDTO> queryPageList(EmployeeListRequestDTO employeeListrequestDTO);

    void updataFailureStatusById(Integer emId);

    void updataSuccessStatusById(Integer emId);

}

