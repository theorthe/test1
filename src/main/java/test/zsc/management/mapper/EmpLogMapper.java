package test.zsc.management.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import test.zsc.management.pojo.EmpLog;

import java.util.List;

@Mapper
public interface EmpLogMapper {
    //插入日志
    @Insert("insert into emp_log (operate_time, info) values (#{operateTime}, #{info})")
    public void insert(EmpLog empLog);

    /**
     * 分页查询日志
     */
    List<EmpLog> list();
}