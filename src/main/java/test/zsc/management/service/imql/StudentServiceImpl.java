package test.zsc.management.service.imql;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.zsc.management.mapper.StudentMapper;
import test.zsc.management.pojo.PageResult;
import test.zsc.management.pojo.Student;
import test.zsc.management.service.StudentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询所有学生（原代码）
     */
    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    /**
     * 分页条件查询学员（新增）
     */
    @Override
    public PageResult page(Integer page, Integer pageSize, String name, Integer degree, Integer clazzId) {
        PageHelper.startPage(page, pageSize);
        List<Student> studentList = studentMapper.list(name, degree, clazzId);
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.save(student);
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    @Override
    public void deleteById(Integer id) {
        studentMapper.deleteById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateById(student);
    }

    /**
     * 学员违纪处理（新增）
     */
    @Override
    public void violation(Integer id, Integer score) {
        // 1. 更新违纪次数和分数
        studentMapper.updateViolation(id, score);
    }
}
