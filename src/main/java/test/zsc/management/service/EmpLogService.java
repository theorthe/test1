package test.zsc.management.service;

import test.zsc.management.pojo.EmpLog;
import test.zsc.management.pojo.PageResult;

public interface EmpLogService {
    //记录新增员工日志
    public void insertLog(EmpLog empLog);

    /**
     * 分页查询日志
     */
    PageResult page(Integer page, Integer pageSize);
}
