package test.zsc.management.service.imql;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.SneakyThrows;

import test.zsc.management.mapper.EmpExprMapper;
import test.zsc.management.mapper.EmpMapper;
import test.zsc.management.pojo.*;
import test.zsc.management.service.EmpLogService;
import test.zsc.management.service.EmpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
/*
  CollectionUtils 功能详解：
  CollectionUtils 是 Spring 框架提供的集合工具类，
  用于简化集合（List、Set、Map 等）的常见操作。
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 员工管理
 */
@Service
public class EmpServiceImpl implements EmpService {

//    Spring 框架中常见的 ‌字段注入（Field Injection）‌ 方式，
//    用于自动装配 MyBatis 的 Mapper 接口依赖。
    @Autowired
    private EmpMapper empMapper;// 注入 EmpMapper

    @Autowired
    private EmpExprMapper empExprMapper; // 注入 EmpExprMapper

    @Autowired
    private EmpLogService empLogService; // 引入 EmpLogService
//    源代码：是没用分页插件com.github.pagehelper的，自己计算分页参数

//    @Override
//    public PageResult page(Integer page, Integer pageSize) {
//        //1. 获取总记录数
//        Long total = empMapper.count();
//
//        //2. 获取结果列表
//        Integer start = (page - 1) * pageSize;
//        List<Emp> empList = empMapper.list(start, pageSize);
//
//        //3. 封装结果
//        return new PageResult(total, empList);
//    }


//    是没有条件的分页查询
//@Override
//public PageResult page(Integer page, Integer pageSize) {
//    //1. 设置分页参数
//    PageHelper.startPage(page,pageSize);
//
//    //2. 执行查询
//    List<Emp> empList = empMapper.list();
//    Page<Emp> p = (Page<Emp>) empList;
//
//    //3. 封装结果
//    return new PageResult(p.getTotal(), p.getResult());
//}


//    @Override
//    public PageResult page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        //1. 设置PageHelper分页参数
//        PageHelper.startPage(page, pageSize);
//        //2. 执行查询
//        List<Emp> empList = empMapper.list(name, gender, begin, end);
//        //3. 封装分页结果
//        Page<Emp> p = (Page<Emp>) empList;
//        return new PageResult(p.getTotal(), p.getResult());



public PageResult page(EmpQueryParam empQueryParam) {
    //1. 设置PageHelper分页参数
    PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
    //2. 执行查询
    List<Emp> empList = empMapper.list(empQueryParam);
    //3. 封装分页结果
    Page<Emp> p = (Page<Emp>)empList;
    return new PageResult<Emp>(p.getTotal(), p.getResult());
}

//    @Transactional 注解是 Spring 框架提供的一个声明式事务管理工具，
//    用于标注在方法或类上，表示该方法或类中的操作需要在一个事务中执行。
//    当方法被 @Transactional 注解标记时，Spring 会自动创建一个事务，
//    并在方法执行完成后提交事务，如果方法执行过程中抛出异常，
//    Spring 会自动回滚事务，以确保数据的一致性和完整性。

//    事务控制主要三步操作：开启事务、提交事务/回滚事务。
//            - 需要在这组操作执行之前，先开启事务  ( start transaction; / begin;)。
//
//            - 所有操作如果全部都执行成功，则提交事务 ( commit; )。
//
//            - 如果这组操作中，有任何一个操作执行失败，都应该回滚事务 ( rollback )。

    /**
     * 新增员工（含事务控制与日志记录）
     * @Transactional: 声明式事务管理
     *   - rollbackFor = Exception.class: 默认 Spring 仅对 RuntimeException 回滚。
     *     配置此项后，即使抛出普通 Exception（受检异常）也会触发事务回滚，防止数据不一致。
     * @SneakyThrows: Lombok 注解，自动将受检异常包装为 RuntimeException 抛出，
     *   避免接口方法签名上强制写 throws Exception，保持代码整洁。
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    /*public void save(Emp emp) {

        //1.补全基础属性
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        //2.保存员工基本信息
        empMapper.insert(emp);

//        int i=1/0; //模拟保存员工信息失败，测试事务回滚
//        以上业务功能save方法在运行时，会引发除0的算术运算异常(运行时异常)，出现异常之后，由于我们在方法上加了@Transactional注解进行事务管理，所以发生异常会执行rollback回滚操作，从而保证事务操作前后数据是一致的。

//        下面我们在做一个测试，我们修改业务功能代码，在模拟异常的位置上直接抛出Exception异常（编译时异常）

        //模拟：异常发生详细见注解回滚
        if(true){
            throw new Exception("出现异常了~~~");
        }
//        在较新版本的 Spring Boot 中，
//        @Transactional 的默认回滚规则已经改变：
//        Spring Boot 1.x: 只回滚RuntimeException 和 ErrorSpring Boot
//        2.x+: 所有异常都回滚（包括受检异常）。

        //3. 保存员工的工作经历信息 - 批量 (稍后完成)
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }
    */
    public void save(Emp emp) {
        try {
            // ================= 事务操作区域 =================
            // Spring AOP 在此处拦截，开启数据库事务 (BEGIN)

            // 1. 补全基础属性
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());

            // 2. 保存员工基本信息
            empMapper.insert(emp);

//            int i = 1/0; // 模拟异常：若放开，整个事务将回滚

            // 3. 保存员工的工作经历信息 - 批量
            Integer empId = emp.getId();
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(empExpr -> empExpr.setEmpId(empId));
                empExprMapper.insertBatch(exprList);
            }
            
            // 若代码执行至此无异常，Spring 将在方法返回前自动提交事务 (COMMIT)
            
        } finally {
            // ================= 日志记录区域 =================
            // finally 块保证无论上方业务成功还是失败，此处必定执行
            
            // 【事务注意点】：由于 insertLog 在当前 @Transactional 方法内部，
            // 它会加入到同一个事务中。这意味着：
            // 1. 业务成功 -> 日志随主事务一起提交。
            // 2. 业务失败回滚 -> 日志插入操作也会被回滚（日志不会入库）。
            // 若需“无论成败都记录日志”，应给 insertLog 加 @Transactional(propagation = Propagation.REQUIRES_NEW)
            
            //EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            //empLogService.insertLog(empLog);
            try {
                EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
                empLogService.insertLog(empLog);
            } catch (Exception e) {
                // 记录日志失败，但不影响主业务
                System.err.println("记录操作日志失败: " + e.getMessage());
            }
        }
    }
    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
        //1. 根据ID批量删除员工基本信息
        empMapper.deleteByIds(ids);

        //2. 根据员工的ID批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }
//    在实现类 EmpServiceImpl 中实现接口方法 deleteByIds
//在删除员工信息时，既需要删除 emp 表中的员工基本信息，还需要删除 emp_expr 表中员工的工作经历信息
//由于删除员工信息，既要删除员工基本信息，又要删除工作经历信息，操作多次数据库的删除，所以需要进行事务控制。


    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional
    @Override
    public void update(Emp emp) {
        //1. 根据ID更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2. 根据员工ID删除员工的工作经历信息 【删除老的】
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //3. 新增员工的工作经历数据 【新增新的】
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }
    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin = empMapper.getUsernameAndPassword(emp);
        if(empLogin != null){
            LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), null);
            return loginInfo;
        }
        return null;
    }
}