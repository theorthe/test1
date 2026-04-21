package test.zsc.management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer id;
    private String name;
//- 如果实体类属性名和数据库表查询返回的字段名一致，mybatis会自动封装。
//- 如果实体类属性名和数据库表查询返回的字段名不一致，不能自动封装。
//
// 解决方案：
//- 手动结果映射
//- 起别名
//- 开启驼峰命名
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

