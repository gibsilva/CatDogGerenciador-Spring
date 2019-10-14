package repositorios;

import entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProdutoRepositorio extends JpaRepository<Produto, Integer>{
    
}