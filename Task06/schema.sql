CREATE DATABASE IF NOT EXISTS campus_events CHARACTER SET utf8mb4;
USE campus_events;
CREATE TABLE IF NOT EXISTS registrations (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 student_id VARCHAR(24) NOT NULL,
 student_name VARCHAR(120) NOT NULL,
 event_name VARCHAR(120) NOT NULL,
 registered_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 CONSTRAINT uq_student_event UNIQUE (student_id, event_name)
);
