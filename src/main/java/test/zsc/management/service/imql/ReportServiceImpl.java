package test.zsc.management.service.imql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.zsc.management.mapper.EmpMapper;
import test.zsc.management.mapper.StudentMapper;
import test.zsc.management.pojo.ClazzOption;
import test.zsc.management.pojo.JobOption;
import test.zsc.management.service.ReportService;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }


    @Override
    public ClazzOption studentCountData() {
        List<Map<String,Object>> list = studentMapper.countStudentClazzData();
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new ClazzOption(clazzList, dataList);

    }

    @Override
    public List<Map<String,Object>> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }

}