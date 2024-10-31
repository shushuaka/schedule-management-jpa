CREATE TABLE User (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      userName VARCHAR(255) NOT NULL UNIQUE,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      modifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Schedule (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          userName VARCHAR(255) NOT NULL,
                          title VARCHAR(255) NOT NULL,
                          content TEXT NOT NULL,
                          createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          modifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          user_id BIGINT,
                          FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

CREATE TABLE UserSchedule (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              user_id BIGINT NOT NULL,
                              schedule_id BIGINT NOT NULL,
                              FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE,
                              FOREIGN KEY (schedule_id) REFERENCES Schedule(id) ON DELETE CASCADE
);

CREATE TABLE Comment (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         schedule_id BIGINT NOT NULL,
                         userName VARCHAR(255) NOT NULL,
                         content TEXT NOT NULL,
                         createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         modifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         FOREIGN KEY (schedule_id) REFERENCES Schedule(id) ON DELETE CASCADE
);
