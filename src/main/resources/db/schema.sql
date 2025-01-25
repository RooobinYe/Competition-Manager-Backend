CREATE TABLE IF NOT EXISTS team (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    com_id INT,
    captain_id INT,
    captain_name VARCHAR(255),
    status INT,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS team_member (
    team_id INT,
    member_id INT,
    PRIMARY KEY (team_id, member_id)
);

CREATE TABLE IF NOT EXISTS team_instructor (
    team_id INT,
    instructor_id INT,
    PRIMARY KEY (team_id, instructor_id)
); 