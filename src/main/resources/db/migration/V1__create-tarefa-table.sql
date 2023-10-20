CREATE TABLE tarefa (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         titulo VARCHAR(100) NOT NULL,
                         descricao VARCHAR(200) NOT NULL,
                         concluida int NOT NULL,
                         datacriacao DATE NOT NULL
);
