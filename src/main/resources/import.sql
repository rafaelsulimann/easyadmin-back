INSERT INTO tb_produtos (nome, preco, descricao) VALUES ('BRINQUEDO DE PLÁSTICO', 200.00, 'TESTE DESCRIÇÃO');
INSERT INTO tb_produtos (nome, preco, descricao) VALUES ('TV', 4000.00, 'TV');
INSERT INTO tb_produtos (nome, preco, descricao) VALUES ('CARRINHO', 500.00, 'CARRO DE BRINQUEDO');
INSERT INTO tb_produtos (nome, preco, descricao) VALUES ('BONÉ', 150.99, 'BONE BRANCO');
INSERT INTO tb_produtos (nome, preco, descricao) VALUES ('BERMUDA', 80.00, 'BERMUDA DE PRAIA');

INSERT INTO tb_categorias (nome) VALUES ('CAMISA');
INSERT INTO tb_categorias (nome) VALUES ('BERMUDA');
INSERT INTO tb_categorias (nome) VALUES ('CALÇA');
INSERT INTO tb_categorias (nome) VALUES ('BONÉ');
INSERT INTO tb_categorias (nome) VALUES ('MOLETOM');
INSERT INTO tb_categorias (nome) VALUES ('BRINQUEDO');
INSERT INTO tb_categorias (nome) VALUES ('ELETRONICO');

INSERT INTO tb_produtos_categorias (produto_id, categoria_id) VALUES (1, 6);
INSERT INTO tb_produtos_categorias (produto_id, categoria_id) VALUES (1, 1);
INSERT INTO tb_produtos_categorias (produto_id, categoria_id) VALUES (1, 2);
INSERT INTO tb_produtos_categorias (produto_id, categoria_id) VALUES (1, 3);
INSERT INTO tb_produtos_categorias (produto_id, categoria_id) VALUES (2, 7);
INSERT INTO tb_produtos_categorias (produto_id, categoria_id) VALUES (3, 6);
INSERT INTO tb_produtos_categorias (produto_id, categoria_id) VALUES (4, 4);
INSERT INTO tb_produtos_categorias (produto_id, categoria_id) VALUES (5, 2);

INSERT INTO tb_usuarios (username, password) VALUES ('ruirenan', '$2a$10$Mo.sAmGingNquBDIMpVROu9WXiA5XamMavrkyqnNvD5kkc5iLNOim');
INSERT INTO tb_usuarios (username, password) VALUES ('rafaelsulimann', '$2a$10$Mo.sAmGingNquBDIMpVROu9WXiA5XamMavrkyqnNvD5kkc5iLNOim');

INSERT INTO tb_roles (authority) VALUES ('ROLE_CLIENT');
INSERT INTO tb_roles (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_usuarios_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_usuarios_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_usuarios_roles (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_vendas (momento, usuario_id, total) VALUES (TIMESTAMP WITH TIME ZONE '2022-07-25T13:00:00Z', 1, 1430.1);
INSERT INTO tb_vendas (momento, usuario_id, total) VALUES (TIMESTAMP WITH TIME ZONE '2022-07-29T15:50:00Z', 2, 1250.0);
INSERT INTO tb_vendas (momento, usuario_id, total) VALUES (TIMESTAMP WITH TIME ZONE '2022-08-03T14:20:00Z', 1, 90.5);

INSERT INTO tb_itens_vendas (venda_id, produto_id, quantidade, sub_total) VALUES (1, 1, 2, 180.1);
INSERT INTO tb_itens_vendas (venda_id, produto_id, quantidade, sub_total) VALUES (1, 3, 1, 1250.0);
INSERT INTO tb_itens_vendas (venda_id, produto_id, quantidade, sub_total) VALUES (2, 3, 1, 1250.0);
INSERT INTO tb_itens_vendas (venda_id, produto_id, quantidade, sub_total) VALUES (3, 1, 1, 90.5);

INSERT INTO tb_pagamentos (venda_id, tipo_pagamento, momento) VALUES (1, 'CREDITO_NAO_PARCELADO', TIMESTAMP WITH TIME ZONE '2022-07-25T15:00:00Z');
INSERT INTO tb_pagamentos (venda_id, tipo_pagamento, momento) VALUES (2, 'DINHEIRO', TIMESTAMP WITH TIME ZONE '2022-07-30T11:00:00Z');
INSERT INTO tb_pagamentos (venda_id, tipo_pagamento, momento) VALUES (3, 'DEBITO', TIMESTAMP WITH TIME ZONE '2022-07-30T11:00:00Z');

