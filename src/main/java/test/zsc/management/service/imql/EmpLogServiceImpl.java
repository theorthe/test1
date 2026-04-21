package test.zsc.management.service.imql;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import test.zsc.management.mapper.EmpLogMapper;
import test.zsc.management.pojo.EmpLog;
import test.zsc.management.pojo.PageResult;
import test.zsc.management.service.EmpLogService;

import java.util.List;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    // Propagation.REQUIRES_NEW: 新启一个事务，如果当前存在事务，则将当前事务挂起
//    Propagation.REQUIRES_NEW  ：不论是否有事务，都创建新事务  ，运行在一个独立的事务中。
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }

    /**
     * 分页查询日志
     */
    @Override
    public PageResult page(Integer page, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);
        // 执行查询
        List<EmpLog> empLogList = empLogMapper.list();
        Page<EmpLog> p = (Page<EmpLog>) empLogList;
        // 封装返回结果
        return new PageResult(p.getTotal(), p.getResult());
    }
}