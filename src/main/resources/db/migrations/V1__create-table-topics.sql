create table topics (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    message VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,

    primary key(id)
);