CREATE TABLE books
(
    id    bigint NOT NULL AUTO_INCREMENT,
    price decimal(8, 2) DEFAULT NULL,
    title varchar(255)  DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE students
(
    id   bigint NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    mark int unsigned DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
