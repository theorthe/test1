package test.zsc.management.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import test.zsc.management.pojo.Result;
import test.zsc.management.pojo.Dept;
import test.zsc.management.service.DeptService;

import java.util.List;

//@RequestMapping("/depts")
// 给控制器添加一个公共的请求路径前缀，后续方法的请求路径都在这个前缀下
//但我用不了，因为@RequestMapping("/depts")会覆盖@RestController的@RequestMapping("/")，
// 我有一个"/depts2"导致请求路径不正确，无法访问到控制器方法。
/**
 * 部门管理控制器
 */
@Slf4j
//lombok中提供的@Slf4j注解，可以简化定义日志记录器这步操作。添加了该注解，就相当于在类中定义了日志记录器，就下面这句代码：
//        private static Logger log = LoggerFactory. getLogger(Xxx. class);
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 查询部门列表
     */
    //@RequestMapping("/depts")
    //要限制请求方式只能用GET，不能用POST、delete、put等其他方式
    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    //或者用@GetMapping("/depts")，效果跟上面一样
    /*
    上述两种方式，在项目开发中，推荐使用第二种方式，简洁、优雅。
     - GET方式：@GetMapping
     - POST方式：@PostMapping
     - PUT方式：@PutMapping
     - DELETE方式：@DeleteMapping
     */
    public Result list(){
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
    @RequestMapping(value = "/depts2", method = RequestMethod.GET)
    public Result list1(){
        List<Dept> deptList = deptService.findAll2();
        return Result.success(deptList);
//        return Result.success();
    }
    /**
     * 根据id删除部门 - delete http://localhost:8080/depts?id=1
     * 在Apifox中，选择delete方式，请求路径为http://localhost:8080/depts
     * 下选参数id=几
     */
    //如果使用@RequestParam，则请求参数必须为id=1，否则会报错。
//    @RequestMapping(value = "/depts", method = RequestMethod.DELETE)
    @DeleteMapping("/depts")
    public Result delete(Integer id){
        System.out.println("根据id删除部门, id=" + id);
        deptService.deleteById(id);
        return Result.success();
    }
    /**
     * 新增部门 - POST http://localhost:8080/depts   请求参数：{"name":"研发部"}
     */
    @PostMapping("/depts")
    public Result save(@RequestBody Dept dept){
        System.out.println("新增部门, dept=" + dept);
        deptService.save(dept);
        return Result.success();
    }
    /**
     * 根据ID查询回显 - GET http://localhost:8080/depts/1
     */
    @GetMapping("/depts/{id}")
    public Result getById(@PathVariable Integer id){
//        System.out.println("根据ID查询, id=" + id);
        log.info("根据ID查询, id: {}" , id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }
    /**
     * 修改部门 - PUT http://localhost:8080/depts  请求参数：{"id":1,"name":"研发部"}
     */
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        System.out.println("修改部门, dept=" + dept);
        deptService.update(dept);
        return Result.success();
    }
}