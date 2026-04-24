package test.zsc.management.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import test.zsc.management.pojo.EmpLog;

import java.util.List;

@Mapper
public interface EmpLogMapper {
    //插入日志
    @Insert("insert into emp_log (operate_emp_id, operate_time, class_name, method_name, " +
            "method_params, return_value, cost_time, operate_emp_name) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, " +
            "#{methodParams}, #{returnValue}, #{costTime}, #{operateEmpName})")
    public void insert(EmpLog empLog);

    /**
     * 分页查询日志
     */
    List<EmpLog> list();
}