package test.zsc.management.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.zsc.management.annotate.LogOperation;
import test.zsc.management.pojo.Clazz;
import test.zsc.management.pojo.PageResult;
import test.zsc.management.pojo.Result;
import test.zsc.management.service.ClazzService;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    // 班级列表条件分页查询（对应文档 3.1）
    @GetMapping
    public Result page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String name,
            String begin,
            String end) {
        log.info("分页查询班级, page: {}, pageSize: {}", page, pageSize);
        PageResult pageResult = clazzService.page(page, pageSize, name, begin, end);
        return Result.success(pageResult);
    }

    // 原来的代码：查询所有班级（对应文档 3.6）
    @GetMapping("/list")
    public Result list() {
        return Result.success(clazzService.findAll());
    }

    //根据ID查询班级（对应文档 3.4）
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询班级, id: {}", id);
        return Result.success(clazzService.getById(id));
    }

    // 删除班级（对应文档 3.2）
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("删除班级, id: {}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    // 添加班级（对应文档 3.3）
    @LogOperation
    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        log.info("新增班级: {}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    // 修改班级（对应文档 3.5）
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级: {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }
}
