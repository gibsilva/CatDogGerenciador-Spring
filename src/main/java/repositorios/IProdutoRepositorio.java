package repositorios;

import entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProdutoRepositorio extends JpaRepository<Produto, Integer>{
	@Query(value = "SELECT id FROM produto order by id desc limit 1", nativeQuery = true)
    public Integer findByLastId();
}