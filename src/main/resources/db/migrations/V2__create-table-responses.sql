create table responses (
    id BIGINT NOT NULL AUTO_INCREMENT,
    response VARCHAR(255) NOT NULL,
    topic_id BIGINT NOT NULL,
    FOREIGN KEY (topic_id) REFERENCES topics(id) ON DELETE CASCADE,
    primary key(id)
);