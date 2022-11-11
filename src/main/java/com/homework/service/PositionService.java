package com.homework.service;

import com.homework.dto.ToggleDepartmentRequestDTO;
import com.homework.dto.TogglePositionRequestDTO;
import com.homework.dto.requestDTO.DepartmentListRequestDTO;
import com.homework.dto.requestDTO.PositionListRequestDTO;
import com.homework.entity.Position;

import java.util.List;
import java.util.Map;

/**
 * 职位表(Position)表服务接口
 *
 * @author makejava
 * @since 2021-05-17 21:29:51
 */
public interface PositionService {

    /**
     * 通过ID查询单条数据
     *
     * @param positionId 主键
     * @return 实例对象
     */
    Position queryById(Integer positionId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Position> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param position 实例对象
     * @return 实例对象
     */
    Position insert(Position position);

    /**
     * 修改数据
     *
     * @param position 实例对象
     * @return 实例对象
     */
    Position update(Position position);

    /**
     * 通过主键删除数据
     *
     * @param positionId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer positionId);

    /**
     * 获取部门信息列表
     * @return
     */
    Map<String, Object> queryByPage(PositionListRequestDTO positionListrequestDTO);

    void toggleStatus(TogglePositionRequestDTO togglepositionRequestDTO);

    Map<String, Object> queryActiveList();

}
