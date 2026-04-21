package test.zsc.management.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.zsc.management.pojo.PageResult;
import test.zsc.management.pojo.Result;
import test.zsc.management.pojo.Student;
import test.zsc.management.service.StudentService;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    //学员列表条件分页查询
    @GetMapping
    public Result page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String name,
            Integer degree,
            Integer clazzId) {
        log.info("分页查询学员, page: {}, pageSize: {}", page, pageSize);
        PageResult pageResult = studentService.page(page, pageSize, name, degree, clazzId);
        return Result.success(pageResult);
    }

    //根据ID查询学员
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询学员, id: {}", id);
        return Result.success(studentService.getById(id));
    }

    //添加学员
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("新增学员: {}", student);
        studentService.save(student);
        return Result.success();
    }

    //修改学员
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员: {}", student);
        studentService.update(student);
        return Result.success();
    }

    // 批量删除学员
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids) {
        log.info("批量删除学员, ids: {}", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    // 违纪处理
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("学员违纪处理, id: {}, score: {}", id, score);
        studentService.violation(id, score);
        return Result.success();
    }
}
