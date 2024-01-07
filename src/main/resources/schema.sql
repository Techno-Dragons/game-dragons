
CREATE TABLE member (
    id bigint generated by default as identity ,
    username varchar(255) unique ,
    email varchar(255),
    nickname varchar(255),
    created_date timestamp,
    modified_date timestamp,
    refresh_token varchar(255),
    primary key (id)
);

CREATE TABLE article (
    id bigint generated by default as identity,
    user_id bigint
    title varchar(255),
    content TEXT,
    category varchar(255),
    created_date timestamp,
    modified_date timestamp,
    foreign key (user_id) references member(user_id),
    primary key (id)
);

CREATE TABLE comment(
    id bigint generated by default as identity ,
    content TEXT,
    created_date timestamp,
    modified_date timestamp,
    foreign key (user_id) references member(user_id),
    foreign key (article_id) references article(article_id)
);

