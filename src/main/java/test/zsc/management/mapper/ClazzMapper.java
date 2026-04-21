package test.zsc.management.mapper;

import org.apache.ibatis.annotations.Mapper;
import test.zsc.management.pojo.Clazz;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 查询所有班级（原代码）
     */
    public List<Clazz> findAll();

    /**
     * 条件分页查询班级（新增）
     */
    List<Clazz> list(String name, String begin, String end);

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
