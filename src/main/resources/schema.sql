CREATE TABLE IF NOT EXISTS team (
    id INT PRIMARY KEY AUTO_INCREMENT,
    com_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    captain_id BIGINT NOT NULL,
    captain_name VARCHAR(255) NOT NULL,
    status INT NOT NULL DEFAULT 0,
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS team_member (
    team_id INT NOT NULL,
    member_id BIGINT NOT NULL,
    PRIMARY KEY (team_id, member_id),
    FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE IF NOT EXISTS team_instructor (
    team_id INT NOT NULL,
    instructor_id BIGINT NOT NULL,
    PRIMARY KEY (team_id, instructor_id),
    FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE IF NOT EXISTS members (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    team_id INT,
    FOREIGN KEY (team_id) REFERENCES teams(id),
    UNIQUE KEY uk_team_email (team_id, email)
); 