package com.homework.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.homework.dto.TogglePositionRequestDTO;
import com.homework.dto.requestDTO.PositionListRequestDTO;
import com.homework.dto.responseDTO.DepartmentActiveListResponseDTO;
import com.homework.dto.responseDTO.PositionActiveListResponseDTO;
import com.homework.dto.responseDTO.PositionListResponseDTO;
import com.homework.entity.Position;
import com.homework.dao.PositionDao;
import com.homework.service.PositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 职位表(Position)表服务实现类
 *
 * @author makejava
 * @since 2021-05-17 21:29:51
 */
@Service("positionService")
public class PositionServiceImpl implements PositionService {
    @Resource
    private PositionDao positionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param positionId 主键
     * @return 实例对象
     */
    @Override
    public Position queryById(Integer positionId) {
        return this.positionDao.queryById(positionId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Position> queryAllByLimit(int offset, int limit) {
        return this.positionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param position 实例对象
     * @return 实例对象
     */
    @Override
    public Position insert(Position position) {
        position.setCreateTime(new Date());
        this.positionDao.insert(position);
        return position;
    }

    /**
     * 修改数据
     *
     * @param position 实例对象
     * @return 实例对象
     */
    @Override
    public Position update(Position position) {
        this.positionDao.update(position);
        return this.queryById(position.getPositionId());
    }

    /**
     * 通过主键删除数据
     *
     * @param positionId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer positionId) {
        return this.positionDao.deleteById(positionId) > 0;
    }

    /**
     * 获取部门信息列表
     *
     * @param positionListrequestDTO
     * @return
     */
    @Override
    public Map<String, Object> queryByPage(PositionListRequestDTO positionListrequestDTO) {
        Map<String,Object> map = new HashMap();
        try {
            PageHelper.startPage(positionListrequestDTO.getPageNum(),positionListrequestDTO.getPageSize());
            Page<PositionListResponseDTO> list = (Page)positionDao.queryPageList(positionListrequestDTO);
            map.put("success",true);
            map.put("data",new PageInfo(list));
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询职位列表失败");
        }
        return map;
    }

    @Override
    public void toggleStatus(TogglePositionRequestDTO togglePositionRequestDTO) {
        Integer status = togglePositionRequestDTO.getStatus();
        if (1==status)
            positionDao.updataFailureStatusById(togglePositionRequestDTO.getPositionId());
        else
            positionDao.updataSuccessStatusById(togglePositionRequestDTO.getPositionId());
    }

    @Override
    public Map<String, Object> queryActiveList() {
        Map<String,Object> map = new HashMap();
        try {
            List<PositionActiveListResponseDTO> departmentList = positionDao.queryActiveList();
            map.put("success",true);
            map.put("data",departmentList);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("获取有效职位列表失败",e);
        }
        return map;
    }
}
