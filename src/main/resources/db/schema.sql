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