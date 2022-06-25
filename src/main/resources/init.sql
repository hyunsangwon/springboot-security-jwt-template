CREATE TABLE IF NOT EXISTS users(
    user_no INTEGER(4) AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '회원 번호',
    user_id VARCHAR(30) COMMENT '회원 아이디',
    user_password VARCHAR(200) COMMENT '회원 비밀번호',
    user_name VARCHAR(20) COMMENT '회원 이름',
    email VARCHAR(30) COMMENT '회원 이메일'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS auth_authority(
   role_id INTEGER(4) AUTO_INCREMENT NOT NULL PRIMARY KEY,
   role_name VARCHAR(20),
   role_description VARCHAR(30)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS auth_users_authority(
    user_no INTEGER(4) NOT NULL,
    role_id INTEGER(4) NOT NULL,
    FOREIGN KEY (user_no) REFERENCES users (user_no) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES auth_authority (role_id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;