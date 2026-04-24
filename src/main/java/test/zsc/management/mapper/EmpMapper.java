package test.zsc.management.mapper;
import org.apache.ibatis.annotations.*;
import test.zsc.management.pojo.Emp;

import test.zsc.management.pojo.EmpQueryParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    /**
     * 查询总记录数，用于分页查询，用了分页插件之后就不需要了，是自己计算的
     */
    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id ")
    public Long count();
    /**
     * 查询所有的员工及其对应的部门名称
     */
//    @Select("select e.*, d.name deptName from emp as e left join dept as d on e.dept_id = d.id limit #{start}, #{pageSize}")
//    public List<Emp> list(Integer start , Integer pageSize);
    /**
     * 查询所有的员工及其对应的部门名称（用github的代码，com.github.pagehelper）
     */
//    注释的代码是没有查询条件的分页查询，现在增加了查询条件：姓名、性别、入职时间范围
//    @Select("select e.*, d.name deptName from emp as e left join dept as d on e.dept_id = d.id")
//    public List<Emp> list();


//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    List<Emp> list(EmpQueryParam empQueryParam);
    /**
     * 查询所有的员工及其对应的部门名称
     */
    @Select("select e.*, d.name as deptName from emp e left join dept d on e.dept_id = d.id")
    public List<Emp> list2();
    /**
     * 新增员工数据
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);
//    主键返回：@Options(useGeneratedKeys = true, keyProperty = "id")
//由于稍后，我们在保存工作经历信息的时候，需要记录是哪位员工的工作经历。 所以，
//保存完员工信息之后，是需要获取到员工的ID的，那这里就需要通过Mybatis中提供的主键返回功能来获取。


    /**
     * 批量删除员工信息
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据ID查询员工详细信息
     */
    Emp getById(Integer id);

    /**
     * 更新员工基本信息
     */
    void updateById(Emp emp);
    /**
     * 根据用户名和密码查询员工信息
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);
    /**
     * 统计各个职位的员工人数
     */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();
    /**
     * 统计员工性别信息
     */
    @MapKey("name")
    List<Map> countEmpGenderData();

    /**
     * 修改员工密码
     */
    @Update("update emp set password = #{password}, update_time = #{updateTime} where id = #{id}")
    void updatePassword(@Param("id") Integer id, @Param("password") String password, @Param("updateTime") java.time.LocalDateTime updateTime);

}