package test.zsc.management.service;

import test.zsc.management.pojo.Clazz;
import test.zsc.management.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    /**
     * 查询所有班级（原代码）
     */
    public List<Clazz> findAll();

    /**
     * 分页条件查询班级（新增）
     */
    PageResult page(Integer page, Integer pageSize, String name, String begin, String end);

    /**
     * 新增班级
     */
    void save(Clazz clazz);
    /**
     * 根据id删除班级
     */
    void deleteById(Integer id);
    /**
     * 根据id查询班级
     */
    Clazz getById(Integer id);
    /**
     * 修改班级
     */
    void update(Clazz clazz);

}
