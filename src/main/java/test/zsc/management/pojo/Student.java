package test.zsc.management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Integer id; //ID
    private String name; //姓名
    private String no; //序号
    private Integer gender; //性别 , 1: 男 , 2 : 女
    private String phone; //手机号
    private String idCard; //身份证号
    private Integer isCollege; //是否来自于院校, 1: 是, 0: 否
    private String address; //联系地址
    private Integer degree; //最高学历, 1: 初中, 2: 高中 , 3: 大专 , 4: 本科 , 5: 硕士 , 6: 博士
    private LocalDate graduationDate; //毕业时间
    private Integer clazzId; //班级ID
    private Short violationCount; //违纪次数
    private Short violationScore; //违纪扣分
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间

    private String clazzName;//班级名称
}
//id int unsigned primary key auto_increment comment 'ID,主键',
//name varchar(10)  not null comment '姓名',
//no char(10)  not null unique comment '学号',
//gender tinyint unsigned  not null comment '性别, 1: 男, 2: 女',
//phone  varchar(11)  not null unique comment '手机号',
//id_card  char(18)  not null unique comment '身份证号',
//is_college tinyint unsigned  not null comment '是否来自于院校, 1:是, 0:否',
//address  varchar(100)  comment '联系地址',
//degree  tinyint unsigned  comment '最高学历, 1:初中, 2:高中, 3:大专, 4:本科, 5:硕士, 6:博士',
//graduation_date date comment '毕业时间',
//clazz_id  int unsigned not null comment '班级ID, 关联班级表ID',
//violation_count tinyint unsigned default '0' not null comment '违纪次数',
//violation_score tinyint unsigned default '0' not null comment '违纪扣分',
//create_time  datetime  comment '创建时间',
//update_time  datetime  comment '修改时间'
