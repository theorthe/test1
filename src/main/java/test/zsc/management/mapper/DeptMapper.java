package test.zsc.management.mapper;

import org.apache.ibatis.annotations.*;
import test.zsc.management.pojo.Dept;
import java.util.List;
@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门
     */
    @Select("select * from dept")
    public List<Dept> findAll();
//    mapper接口方法的返回值类型是List<Dept>，
//    mybatis会根据查询结果集中的字段名和Dept类中的属性名进行自动封装。
//    但是查询结果集中的字段名是create_time和update_time，
//    而Dept类中的属性名是createTime和updateTime，mybatis无法自动封装这两个字段的值到Dept对象中。
//    1). 手动结果映射
//在DeptMapper接口方法上，通过 @Results及@Result 进行手动结果映射。
   @Results({@Result(column = "create_time", property = "createTime"),
          @Result(column = "update_time", property = "updateTime")})
   @Select("select /*id,*/ name, create_time, update_time from dept")
          public List<Dept> findAll2();

//    2). 起别名
//    @Select("select id, name as name2, create_time, update_time from dept")
//    public List<Dept> findAll2();

//    3). 开启驼峰命名
//    在mybatis的全局配置文件中，开启驼峰命名：
//    <configuration>
//        <settings>
//            <setting name="mapUnderscoreToCamelCase" value="true"/>
//        </settings>
//    @Select("select id, name, create_time, update_time from dept")
    /**
     * 根据id删除部门
     */
    @Delete("delete from dept where id = #{id}")
       void deleteById(Integer id);
    /**
     * 添加部门
     */
    //用占位符插入数据（预编译），#{name}会被替换为dept对象中的name属性值，
    // #{createTime}会被替换为dept对象中的createTime属性值，
    // #{updateTime}会被替换为dept对象中的updateTime属性值。

    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);
    /**
     * 根据ID查询部门数据
     */
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);
    /**
     * 更新部门
     */
    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
