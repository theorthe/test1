package test.zsc.management.service.imql;

import test.zsc.management.service.DeptService;
import org.springframework.stereotype.Service;
import test.zsc.management.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import test.zsc.management.pojo.Dept;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    public List<Dept> findAll() {
        return deptMapper.findAll();
    }
    public List<Dept> findAll2() {
        return deptMapper.findAll2();
    }

//    public void add(Dept dept){return deptMapper.add();}
    public void deleteById(Integer id) {
     deptMapper.deleteById(id);
    }
    public void  save(Dept dept) {
        //补全基础属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //保存部门
        deptMapper.insert(dept);
    }
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }
    public void update(Dept dept) {
        //补全基础属性
        dept.setUpdateTime(LocalDateTime.now());
        //保存部门
        deptMapper.update(dept);
    }

}

