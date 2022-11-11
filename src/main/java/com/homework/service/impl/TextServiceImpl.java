package com.homework.service.impl;

import com.homework.entity.Text;
import com.homework.dao.TextDao;
import com.homework.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Text)表服务实现类
 *
 * @author makejava
 * @since 2021-05-13 01:22:20
 */
@Service
public class TextServiceImpl implements TextService {

    @Resource
    private TextDao textDao;

//    @Autowired
//    private TextService textDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Text queryById(Integer id) {
        return textDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Text> queryAllByLimit(int offset, int limit) {
        return this.textDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param text 实例对象
     * @return 实例对象
     */
    @Override
    public Text insert(Text text) {
        this.textDao.insert(text);
        return text;
    }

    /**
     * 修改数据
     *
     * @param text 实例对象
     * @return 实例对象
     */
    @Override
    public Text update(Text text) {
        this.textDao.update(text);
        return this.queryById(text.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return textDao.deleteById(id);

    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
//    @Override
//    public Text queryById(Integer id) {
//        return this.textDao.queryById(id);
//    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
//    public Text queryById(Integer id) {
//        return textDao.queryById(id);
//    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */

//    public List<Text> queryAllByLimit(int offset, int limit) {
//        return this.textDao.queryAllByLimit(offset, limit);
//    }

    /**
     * 新增数据
     *
     * @param text 实例对象
     * @return 实例对象
     */

//    public Text insert(Text text) {
//        this.textDao.insert(text);
//        return text;
//    }

    /**
     * 修改数据
     *
     * @param text 实例对象
     * @return 实例对象
     */

//    public Text update(Text text) {
//        this.textDao.update(text);
//        return this.queryById(text.getId());
//    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
//    public boolean deleteById(Integer id) {
//        return textDao.deleteById(id);
//    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */

//    public boolean deleteById(Integer id) {
//        return textDao.deleteById(id) > 0;
//    }
}
