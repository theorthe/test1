package test.zsc.management.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import test.zsc.management.pojo.Emp;
import test.zsc.management.pojo.EmpLog;
import test.zsc.management.pojo.EmpQueryParam;
import test.zsc.management.pojo.PageResult;
import test.zsc.management.pojo.Result;
import test.zsc.management.service.EmpLogService;
import test.zsc.management.service.EmpService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Slf4j
//一定要有@RestController注解，否则无法将控制器类注册到Spring容器中，导致无法处理HTTP请求，访问控制器方法时会出现404错误。
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @Autowired
    private EmpLogService empLogService;
//    @GetMapping
////    @RequestParam(defaultValue="默认值")   //设置请求参数默认值
//    public Result page(@RequestParam(defaultValue = "1") Integer page ,
//                       @RequestParam(defaultValue = "10") Integer pageSize){
//        log.info("查询员工信息, page={}, pageSize={}", page, pageSize);
//        PageResult pageResult = empService.page(page, pageSize);
//        return Result.success(pageResult);
//    }



//@GetMapping
//public Result page(@RequestParam(defaultValue = "1") Integer page,
//                   @RequestParam(defaultValue = "10") Integer pageSize,
//                   String name, Integer gender,
//                   @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                   @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
//    log.info("查询请求参数： {}, {}, {}, {}, {}, {}", page, pageSize, name, gender, begin, end);
//    PageResult pageResult = empService.page(page, pageSize, name, gender, begin, end);
//    return Result.success(pageResult);
//}


@GetMapping
public Result page(EmpQueryParam empQueryParam) {
    log.info("查询请求参数： {}", empQueryParam);
    PageResult<Emp> pageResult = empService.page(empQueryParam);
    return Result.success(pageResult);
}

/**
 * 查询所有员工
 */
@GetMapping("/list")
public Result list() {
    log.info("查询所有员工列表");
    EmpQueryParam empQueryParam = new EmpQueryParam();
    empQueryParam.setPage(1);
    empQueryParam.setPageSize(1000);
    PageResult<Emp> pageResult = empService.page(empQueryParam);
    return Result.success(pageResult.getRows());
}

/**
 * 添加员工
 * @param emp
 *   需要添加的员工信息，使用@RequestBody注解将请求体中的JSON数据转换为Emp对象
 */
@PostMapping
    public Result save(@RequestBody(required = false) Emp emp)throws Exception{
        if(emp == null){
            return Result.error("请求数据不能为空");
        }
        log.info("保存员工信息, emp={}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 批量删除员工
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除部门: ids={} ", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 查询回显
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询员工的详细信息");
        Emp emp  = empService.getInfo(id);
        return Result.success(emp);
    }
    /**
     * 更新员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息, {}", emp);
        empService.update(emp);
        return Result.success();
    }
}