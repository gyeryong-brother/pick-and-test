CREATE TABLE post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    time DATETIME NOT NULL
);

-- 만약 PostgreSQL을 사용한다면, AUTO_INCREMENT 대신 SERIAL 또는 BIGSERIAL을 사용하세요.
-- 예시: id BIGSERIAL PRIMARY KEY,

-- 참고: CommentEntity는 PostEntity와 OneToMany 관계이므로,
-- CommentEntity 테이블은 별도로 생성되어야 하며
-- PostEntity의 'id'를 참조하는 'post_id'와 같은 외래 키(Foreign Key)를 가질 것입니다.
-- 여기서는 PostEntity 테이블만 다룹니다.