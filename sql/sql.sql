create database ratel_singleton charset utf8;
use ratel_singleton;
create table user
(
    id            bigint auto_increment        primary key,
    username      varchar(20)            not null comment '用户名',
    password      varchar(20)            not null comment '密码',
    real_name varchar(20)   not null comment '真实姓名',
    email         varchar(50) default '' not null comment '邮箱',
    phone         varchar(20)            not null comment '电话',
    sex           tinyint(1)      default -1 not null comment '性别',
    age           int(3)       not null comment '年龄',
    register_from varchar(10)            not null comment '注册来源',
    create_time datetime default CURRENT_TIMESTAMP comment '创建时间',
    modify_time datetime default CURRENT_TIMESTAMP ON UPDATE  CURRENT_TIMESTAMP comment '修改时间',
    constraint user_email_uindex
        unique (email),
    constraint user_phone_uindex
        unique (phone),
    constraint user_username_uindex
        unique (username)
)charset utf8
    comment '用户表';


create table role(
    id bigint auto_increment primary key ,
    role_name varchar(20) not null comment '角色名',
    parent_id bigint not null comment '父id',
    create_time datetime default CURRENT_TIMESTAMP comment '创建时间',
    modify_time datetime default CURRENT_TIMESTAMP ON UPDATE  CURRENT_TIMESTAMP comment '修改时间'
) charset utf8 comment '角色表';


create table user_role_relation(
    id bigint auto_increment primary key ,
    user_id bigint not null comment '用户id',
    role_id bigint not null comment '角色id',
    enable boolean default true comment '是否生效'
)charset utf8 comment '用户角色关系表';

create table menu(
    id bigint auto_increment primary key,
    role_id bigint not null comment '角色id',
    url varchar(50) not null comment '菜单URL',
    role_icon varchar(50) not null comment '菜单图标',
    parent_id bigint not null default 0 comment '父id',
    relation_code varchar(100) not null default -1 comment '方便查询'
);


create table access_log(
    id bigint auto_increment primary key,
    ip varchar(20) comment '访问来源IP',
    user_id bigint comment '用户ID',
    Operation_Content varchar(20) comment '操作内容',
    Operation_param varchar(200) comment '请求参数',
    assess_time datetime default CURRENT_TIMESTAMP comment '访问时间',
    assess_url varchar(20) comment '访问URL',
    source_url varchar(20) comment '来源网址'
)