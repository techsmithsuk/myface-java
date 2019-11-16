CREATE TABLE comments 
(
    id          int unsigned not null auto_increment,
    sender_id   int unsigned not null,
    post_id     int unsigned not null,
    message     varchar(512) not null,
    sent_at     datetime not null,
    
    primary key (id),
    foreign key (sender_id) references users(id),
    foreign key (post_id) references posts(id)
);