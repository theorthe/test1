package test.zsc.management.service;

import test.zsc.management.pojo.PageResult;
import test.zsc.management.pojo.Student;

import java.util.List;

public interface StudentService {
    /**
     * 查询所有学生（原代码）
     */
    List<Student> findAll();

    /**
     * 分页条件查询学员（新增）
     */
    PageResult page(Integer page, Integer pageSize, String name, Integer degree, Integer clazzId);

    /**
     * 新增学生
     * @param student
     */
    void save(Student student);
    /**
     * 根据id删除学生
     */
    void deleteById(Integer id);
    /**
     * 批量删除学员
     */
    void deleteByIds(List<Integer> ids);
    /**
     * 根据id查询学生
     */
    Student getById(Integer id);
    /**
     * 修改学生
     */
    void update(Student student);

    /**
     * 学员违纪处理（新增）
     */
    void violation(Integer id, Integer score);

}
