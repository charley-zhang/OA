package com.homework.service;

import com.homework.entity.Text;

import java.util.List;

/**
 * (Text)表服务接口
 *
 * @author makejava
 * @since 2021-05-13 01:22:19
 */
public interface TextService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Text queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Text> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param text 实例对象
     * @return 实例对象
     */
    Text insert(Text text);

    /**
     * 修改数据
     *
     * @param text 实例对象
     * @return 实例对象
     */
    Text update(Text text);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteById(Integer id);

}
