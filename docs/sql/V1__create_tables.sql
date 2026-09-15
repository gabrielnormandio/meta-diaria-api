-- Script de migração V1: Criação da estrutura de tabelas do Meta Diária API

CREATE TABLE tb_usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    horario_notificacao TIME NULL,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tb_planejamentos_mensais (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    mes_ano DATE NOT NULL,
    meta_mensal NUMERIC(10, 2) NOT NULL CHECK (meta_mensal > 0),
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_planejamento_usuario FOREIGN KEY (usuario_id) REFERENCES tb_usuarios(id) ON DELETE CASCADE,
    CONSTRAINT uk_usuario_mes_ano UNIQUE (usuario_id, mes_ano)
);

CREATE TABLE tb_dias_planejados (
    id BIGSERIAL PRIMARY KEY,
    planejamento_id BIGINT NOT NULL,
    data DATE NOT NULL,
    eh_dia_trabalho BOOLEAN NOT NULL DEFAULT TRUE,
    meta_diaria_atual NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    ganho_bruto NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
    CONSTRAINT fk_dia_planejamento FOREIGN KEY (planejamento_id) REFERENCES tb_planejamentos_mensais(id) ON DELETE CASCADE
);

CREATE TABLE tb_saidas_financeiras (
    id BIGSERIAL PRIMARY KEY,
    dia_planejado_id BIGINT NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    valor NUMERIC(10, 2) NOT NULL CHECK (valor > 0),
    CONSTRAINT fk_saida_dia_planejado FOREIGN KEY (dia_planejado_id) REFERENCES tb_dias_planejados(id) ON DELETE CASCADE
);
