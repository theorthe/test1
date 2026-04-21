package test.zsc.management.service;

import test.zsc.management.pojo.ClazzOption;
import test.zsc.management.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计各个职位的员工人数
     * @return
     */
    JobOption getEmpJobData();
    /**
     * 统计员工性别信息
     */
    List<Map> getEmpGenderData();
    /**
     * 统计学员学历信息
     */
    List<Map<String,Object>> getStudentDegreeData();
    /**
     * 统计各个班级的学员人数
     */
    ClazzOption studentCountData();
}
