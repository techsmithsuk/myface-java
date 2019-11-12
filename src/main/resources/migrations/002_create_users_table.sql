CREATE TABLE `users`
(
    id 					int unsigned not null auto_increment,
    user_name			varchar (256) unique not null,
    email				varchar (512) unique not null,
    first_name			varchar (256) not null,
    last_name			varchar (256) not null,
    profile_image		varchar (1024),
    banner_image		varchar (1024),

    primary key (id)
);