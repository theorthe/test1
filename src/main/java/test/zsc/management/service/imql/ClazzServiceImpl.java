package test.zsc.management.service.imql;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.zsc.management.mapper.ClazzMapper;
import test.zsc.management.pojo.Clazz;
import test.zsc.management.pojo.PageResult;
import test.zsc.management.service.ClazzService;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 查询所有班级（原代码）
     */
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }

    /**
     * 分页条件查询班级（新增）
     */
    @Override
    public PageResult page(Integer page, Integer pageSize, String name, String begin, String end) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);
        // 执行查询
        List<Clazz> clazzList = clazzMapper.list(name, begin, end);
        Page<Clazz> p = (Page<Clazz>) clazzList;
        // 封装返回结果
        return new PageResult(p.getTotal(), p.getResult());
    }

    /**
     * 添加班级
     */
    public void save(Clazz clazz) {
        clazzMapper.save(clazz);
    }
    /**
     * 修改班级
     */
    public void update(Clazz clazz) {
        clazzMapper.update(clazz);
    }
    /**
     * 删除班级
     */
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }
    /**
     * 根据id查询班级
     */
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }
}
