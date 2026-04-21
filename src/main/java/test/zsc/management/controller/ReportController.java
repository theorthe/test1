package test.zsc.management.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.zsc.management.pojo.ClazzOption;
import test.zsc.management.pojo.JobOption;
import test.zsc.management.pojo.Result;
import test.zsc.management.service.ReportService;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计各个职位的员工人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计各个职位的员工人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }
    /**
     * 统计员工性别信息
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别信息");
        List<Map> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }
    /**
     * 统计学员学历信息
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学员学历信息");
        List<Map<String,Object>> degreelist = reportService.getStudentDegreeData();
        return Result.success(degreelist);
    }
    /**
     * 统计各个班级的学员人数
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计各个班级的学员人数");
        ClazzOption clazzOption = reportService.studentCountData();
        return Result.success(clazzOption);
    }

}
