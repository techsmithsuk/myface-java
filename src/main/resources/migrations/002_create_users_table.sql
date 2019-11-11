CREATE TABLE `users`
(
    user_name			varchar (256) not null,
    email				varchar (512) unique not null,
    first_name			varchar (256) not null,
    last_name			varchar (256) not null,
    profile_image		varchar (1024),
    banner_image		varchar (1024),

    primary key (user_name)
);