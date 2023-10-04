package com.easyadmin.easyadmin.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.easyadmin.easyadmin.models.Categoria;
import com.easyadmin.easyadmin.models.Produto;
import com.easyadmin.easyadmin.models.Usuario;
import com.easyadmin.easyadmin.models.Venda;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

public class SpecificationTemplate {
    
    @And({
        @Spec(path = "nome", spec = LikeIgnoreCase.class),
        @Spec(path = "descricao", spec = LikeIgnoreCase.class),
    })
    public interface ProdutoSpec extends Specification<Produto>{}

    @And({
        @Spec(path = "nome", spec = LikeIgnoreCase.class)
    })
    public interface CategoriaSpec extends Specification<Categoria>{}

    @And({
        @Spec(path = "username", spec = LikeIgnoreCase.class)
    })
    public interface UsuarioSpec extends Specification<Usuario>{}
    
    @And({
        @Spec(path = "pagamento.tipoPagamento", spec = Equal.class),
        @Spec(path = "usuario.username", spec = LikeIgnoreCase.class),
    })
    public interface VendaSpec extends Specification<Venda>{}
}
