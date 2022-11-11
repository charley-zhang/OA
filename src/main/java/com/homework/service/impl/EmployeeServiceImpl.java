package com.homework.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.homework.dao.DepartmentDao;
import com.homework.dao.PositionDao;
import com.homework.dto.EmployeeDTO;
import com.homework.dto.requestDTO.EmployeeListRequestDTO;
import com.homework.dto.requestDTO.ToggleEmployeeRequestDTO;
import com.homework.dto.responseDTO.DepartmentActiveListResponseDTO;
import com.homework.dto.responseDTO.EmployeeResponseDTO;
import com.homework.dto.responseDTO.PositionActiveListResponseDTO;
import com.homework.entity.Department;
import com.homework.entity.Employee;
import com.homework.dao.EmployeeDao;
import com.homework.service.EmployeeService;
import com.homework.util.Md5til;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工表(Employee)表服务实现类
 *
 * @author makejava
 * @since 2021-05-17 21:11:29
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private PositionDao positionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param emId 主键
     * @return 实例对象
     */
    @Override
    public EmployeeResponseDTO queryById(Integer emId) throws Exception{
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        try{
            /**
             * 获取部门下拉列表
             * 获取职位下拉列表
             * 获取员工信息
             */
            EmployeeDTO employeeDTO = employeeDao.queryById(emId);
            List<DepartmentActiveListResponseDTO> departmentActiveListResponseDTOS = departmentDao.queryActiveList();
            List<PositionActiveListResponseDTO> positionActiveListResponseDTOS = positionDao.queryActiveList();
            employeeResponseDTO.setEmployeeDTO(employeeDTO);
            employeeResponseDTO.setDepartmentList(departmentActiveListResponseDTOS);
            employeeResponseDTO.setPositionList(positionActiveListResponseDTOS);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return employeeResponseDTO;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Employee> queryAllByLimit(int offset, int limit) {
        return this.employeeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public Employee insert(Employee employee) {
        employee.setPassword(Md5til.getMd5("123456"));
        this.employeeDao.insert(employee);
        return employee;
    }

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(Employee employee) {
        return employeeDao.update(employee);
    }

    /**
     * 通过主键删除数据
     *
     * @param emId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer emId) {
        return this.employeeDao.deleteById(emId) > 0;
    }

    @Override
    public EmployeeDTO GetEmIfnoByUsernameAndPassword(String username, String password) {
        return employeeDao.queryEmIfnoByUsernameAndPassword(username, Md5til.getMd5(password));
    }

    @Override
    public Map<String, Object> queryByPage(EmployeeListRequestDTO employeeListrequestDTO) {
        Map<String,Object> map = new HashMap();
        try {
            PageHelper.startPage(employeeListrequestDTO.getPageNum(),employeeListrequestDTO.getPageSize());
            Page<EmployeeDTO> list = (Page)employeeDao.queryPageList(employeeListrequestDTO);
            map.put("success",true);
            map.put("data",new PageInfo(list));
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询员工列表失败");
        }
        return map;
    }

    @Override
    public void toggleStatus(ToggleEmployeeRequestDTO toggleemployeeRequestDTO) {
        Integer status = toggleemployeeRequestDTO.getStatus();
        if (1==status)
            employeeDao.updataFailureStatusById(toggleemployeeRequestDTO.getEmId());
        else
            employeeDao.updataSuccessStatusById(toggleemployeeRequestDTO.getEmId());
    }
}
