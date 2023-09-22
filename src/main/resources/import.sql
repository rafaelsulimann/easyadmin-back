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
INSERT INTO tb_produtos_categorias (produto_id, categoria_id) VALUES (2, 7);
INSERT INTO tb_produtos_categorias (produto_id, categoria_id) VALUES (3, 6);
INSERT INTO tb_produtos_categorias (produto_id, categoria_id) VALUES (4, 4);
INSERT INTO tb_produtos_categorias (produto_id, categoria_id) VALUES (5, 2);

