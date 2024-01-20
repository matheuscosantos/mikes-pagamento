CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE IF NOT EXISTS pagamento_pedido (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    id_pedido VARCHAR(36) NOT NULL,
    status VARCHAR(50) NOT NULL,
    criado_em TIMESTAMP NOT NULL,
    atualizado_em TIMESTAMP NOT NULL
);