CREATE DATABASE schedule_management;

USE schedule_management;

CREATE TABLE Author (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,    -- 고유 식별자
                        name VARCHAR(50) NOT NULL,               -- 작성자 이름
                        email VARCHAR(100) UNIQUE NOT NULL,      -- 작성자 이메일, 중복 불가
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,      -- 생성 시간
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정 시간
);

-- Schedule 테이블 생성
CREATE TABLE Schedule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,    -- 고유 식별자
                          title VARCHAR(100) NOT NULL,             -- 스케줄 제목
                          description TEXT,                        -- 스케줄 설명
                          date DATE NOT NULL,                      -- 스케줄 날짜
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,      -- 생성 시간
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정 시간
                          author_id BIGINT,                        -- 작성자 ID, 외래 키로 참조
                          FOREIGN KEY (author_id) REFERENCES Author(id) -- 작성자 테이블과의 관계 정의
);
