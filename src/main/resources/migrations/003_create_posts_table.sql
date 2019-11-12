CREATE TABLE `posts`
(
    id					int unsigned not null auto_increment,
    sender_user_id		int unsigned not null,
    receiver_user_id	int unsigned not null,
    message				varchar (1024) not null,
    image				varchar (1024),

    primary key (id),
    foreign key (sender_user_id) references users(id),
    foreign key (receiver_user_id) references users(id)
);