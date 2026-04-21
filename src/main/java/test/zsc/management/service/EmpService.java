package test.zsc.management.service;

import test.zsc.management.pojo.Emp;
import test.zsc.management.pojo.EmpQueryParam;
import test.zsc.management.pojo.LoginInfo;
import test.zsc.management.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 分页查询
//    * @param page 页码
//    * @param pageSize 每页记录数
     */
//    原有代码是没有查询条件的分页查询，现在增加了查询条件：姓名、性别、入职时间范围
    //这里是直接把分页参数和查询条件封装在EmpQueryParam对象中，传递给service层，service层再调用mapper层进行查询
    //PageResult page(Integer page, Integer pageSize);
//    PageResult page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    //增加了查询条件：姓名、性别、入职时间范围(用了PageResult封装了分页参数和查询条件)
    PageResult<Emp> page(EmpQueryParam empQueryParam);
    /**
     * 新增员工
     * @param emp
     */
    void save(Emp emp) throws Exception;
    /**
     * 批量删除员工
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据ID查询员工的详细信息
     */
    Emp getInfo(Integer id);
    /**
     * 更新员工信息
     * @param emp
     */
    void update(Emp emp);
    /**
     * 登录
     */
    LoginInfo login(Emp emp);
}
