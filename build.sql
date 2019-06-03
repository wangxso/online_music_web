create table administor
(
    username  char(20) not null,
    password  char(20) not null,
    authority int      not null,
    constraint administor_username_uindex
        unique (username)
)
    comment '管理员';

alter table administor
    add primary key (username);

create table comment
(
    user_id    char(20)                  null,
    music_id   char(20)                  null,
    comment    varchar(255) charset utf8 null,
    comment_id int auto_increment
        primary key
)
    comment '评论';

create table music
(
    music_id  char(15) charset latin1     not null,
    name      varchar(50)                 null,
    author    varchar(50)                 null,
    publisher varchar(50)                 null,
    time      double                      null,
    src       varchar(255) charset latin1 null,
    lyric_src varchar(255) charset latin1 null,
    constraint music_music_id_uindex
        unique (music_id)
)
    comment '音乐' charset = utf8;

alter table music
    add primary key (music_id);

create table user
(
    user_id         char(10)     not null,
    password        char(20)     not null,
    level           int          not null,
    email           char(50)     null,
    create_time     datetime     not null,
    last_login_time datetime     null,
    nick_name       varchar(255) null,
    phone           char(20)     not null,
    constraint nick_name_UNIQUE
        unique (nick_name),
    constraint user_user_id_uindex
        unique (user_id)
)
    comment '用户';

alter table user
    add primary key (user_id);

create table listen_list
(
    listen_list_id int      not null,
    user_id        char(20) null,
    music_id       char(20) null,
    constraint listen_list_listen_list_id_uindex
        unique (listen_list_id),
    constraint listen_list_music_music_id_fk
        foreign key (music_id) references music (music_id)
            on update cascade on delete set null,
    constraint listen_list_user_user_id_fk
        foreign key (user_id) references user (user_id)
            on update cascade on delete cascade
)
    comment '听歌列表';

alter table listen_list
    add primary key (listen_list_id);

