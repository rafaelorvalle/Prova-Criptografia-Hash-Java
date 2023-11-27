create database teste;
use teste;

-- Criar tabela paciente
CREATE TABLE paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL
);

-- Criar tabela medico
CREATE TABLE medico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    especialidade VARCHAR(100) NOT NULL
);

-- Criar tabela exame_hdl
CREATE TABLE exame_hdl (
    id INT AUTO_INCREMENT PRIMARY KEY,
    resultado VARCHAR(100) NOT NULL, 
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
	jejum int NOT NULL,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id),
    FOREIGN KEY (id_medico) REFERENCES medico(id)

);

-- Criar tabela valor_padrao
CREATE TABLE valor_padrao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    limite_inferior DECIMAL(10, 2),
    limite_superior DECIMAL(10, 2),
    unidade VARCHAR(50) NOT NULL,
    valor_referencia VARCHAR(100)
);

-- Criar da tabela senha
CREATE TABLE senha (
    id INT AUTO_INCREMENT PRIMARY KEY,
    chave_secreta VARCHAR(100) NOT NULL
);

-- Criar da tabela usuário
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(64) NOT NULL
);

-- Inserindo alguns médicos: 
INSERT INTO medico (nome, cpf, especialidade) VALUES
('Dr. Bob', '123.456.789-01', 'Imunologia');

INSERT INTO medico (nome, cpf, especialidade) VALUES
('Dr. Marley', '123.456.789-02', 'Neurologia');

INSERT INTO medico (nome, cpf, especialidade) VALUES
('Dr. San', '123.456.789-03', 'Pediatria');

INSERT INTO medico (nome, cpf, especialidade) VALUES
('Dr. Torino', '123.456.789-04', 'Cardiologia');

INSERT INTO medico (nome, cpf, especialidade) VALUES
('Dr. Gray', '123.456.789-05', 'Alguma coisa-ia');

-- Inserindo alguns pacientes
INSERT INTO paciente (nome, cpf) VALUES
('Alice', '123.456.789-06');

INSERT INTO paciente (nome, cpf) VALUES
('Cooper', '123.456.789-07');

INSERT INTO paciente (nome, cpf) VALUES
('Togata', '123.456.789-08');

INSERT INTO paciente (nome, cpf) VALUES
('Mirio', '123.456.789-09');

-- Inserindo os valores padrão
INSERT INTO valor_padrao(limite_inferior, limite_superior, unidade, valor_referencia) VALUES
(40.0, 100.0, 'mg/dL', 'Acima de 60 mg/dL'); 

