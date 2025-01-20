create table users (
    
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(300) NOT NULL,

    primary key(id)
);