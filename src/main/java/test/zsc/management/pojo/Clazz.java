package test.zsc.management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {
    private Integer id; //ID
    private String name; //班级名称
    private String room; //班级教室
    private LocalDate beginDate; //开课时间
    private LocalDate endDate; //结课时间
    private Integer masterId; //班主任
    private Integer subject; //学科
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间

    private String masterName; //班主任姓名
    private String status; //班级状态 - 未开班 , 在读 , 已结课

}
//id   int unsigned primary key auto_increment comment 'ID,主键',
//name  varchar(30) not null unique  comment '班级名称',
//room  varchar(20) comment '班级教室',
//begin_date date not null comment '开课时间',
//end_date date not null comment '结课时间',
//master_id int unsigned null comment '班主任ID, 关联员工表ID',
//subject tinyint unsigned not null comment '学科, 1:java, 2:前端, 3:大数据, 4:Python, 5:Go, 6: 嵌入式',
//create_time datetime  comment '创建时间',
//update_time datetime  comment '修改时间'