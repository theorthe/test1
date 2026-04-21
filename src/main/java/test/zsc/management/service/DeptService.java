package test.zsc.management.service;
import test.zsc.management.pojo.Dept;
import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门
     */
    public List<Dept> findAll();


    public List<Dept> findAll2();
    /**
     * 添加部门
     */
    //    public void add(Dept dept);
    /**
     * 新增部门
     */
    void save(Dept dept);
    /**
     * 根据id删除部门
     */
    void deleteById(Integer id);
    /**
     * 根据id查询部门
     */
    Dept getById(Integer id);
    /**
     * 修改部门
     */
    void update(Dept dept);
}
