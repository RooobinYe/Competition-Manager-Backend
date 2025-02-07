SET time_zone = '+00:00';

CREATE TABLE IF NOT EXISTS team (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL, 
    com_id INT,
    captain_id INT,
    captain_name VARCHAR(255),
    status INT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    member_names TEXT,
    instructor_names TEXT
);

CREATE TABLE IF NOT EXISTS member (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    student_id VARCHAR(50) NOT NULL UNIQUE,
    team_id INT,
    academy_id INT,
    phone VARCHAR(20),
    is_captain INT DEFAULT 0,
    email VARCHAR(255),
    FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE IF NOT EXISTS instructor (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    work_code VARCHAR(50) NOT NULL UNIQUE,
    team_id INT,
    academy_id INT,
    phone VARCHAR(20),
    FOREIGN KEY (team_id) REFERENCES team(id)
); 

CREATE TABLE IF NOT EXISTS user (
    user_code VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    academy_id INT,
    com_id INT,
    role INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS competition (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    min_team_members INT,
    max_team_members INT,
    work_code VARCHAR(50) NOT NULL,
    start_time DATETIME,
    end_time DATETIME,
    review_begin_time DATETIME,
    review_end_time DATETIME,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
