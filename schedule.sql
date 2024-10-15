CREATE DATABASE schedule_management;

USE schedule_management;

CREATE TABLE Author (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,    -- 고유 식별자
                        name VARCHAR(50) NOT NULL,               -- 작성자 이름
                        email VARCHAR(100) UNIQUE NOT NULL,      -- 작성자 이메일, 중복 불가
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,      -- 생성 시간
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정 시간
);
