package test.zsc.management.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.zsc.management.pojo.PageResult;
import test.zsc.management.pojo.Result;
import test.zsc.management.service.EmpLogService;

@Slf4j
@RequestMapping("/log")
@RestController
public class EmpLogController {

    @Autowired
    private EmpLogService empLogService;

    /**
     * 日志分页查询
     */
    @GetMapping("/page")
    public Result page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询日志, page: {}, pageSize: {}", page, pageSize);
        PageResult pageResult = empLogService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
