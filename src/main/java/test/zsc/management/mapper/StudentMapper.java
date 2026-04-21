package test.zsc.management.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import test.zsc.management.pojo.ClazzOption;
import test.zsc.management.pojo.Emp;
import test.zsc.management.pojo.Student;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    /**
     * 查询所有学员（原代码）
     */
    List<Student> findAll();

    /**
     * 条件分页查询学员（新增）
     */
    List<Student> list(String name, Integer degree, Integer clazzId);

    /**
     * 增加学员
     */
    void save(Student student);


    /**
     * 根据ID查询学员详细信息
     * 有返回值，返回值类型为Student，要在配置文件StudentMapper.xml中配置
     * * <select id="getById" parameterType="Integer" resultType="test.zsc.management.pojo.Student">
     *  如果我定义了 自定义结果集ResultMap
     * * <select id="getById" parameterType="Integer" resultMap="studentResultMap">   \
     */
    Student getById(Integer id);
    /**
     * 批量删除学员
     */
    void deleteByIds(List<Integer> ids);
    /**
     * 根据id删除学员
     */
    void deleteById(Integer id);

    /**
     * 更新学员基本信息
     */
    void updateById(Student student);

    /**
     * 学员违纪处理（新增）
     * @Param id
     * @Param score
     */
    void updateViolation(Integer id, Integer score);

    /**
     * 统计各个班级的学员人数
     */
    List<Map<String,Object>> countStudentClazzData();
    /**
     * 统计学员学历信息
     */
    List<Map<String,Object>> countStudentDegreeData();


}
