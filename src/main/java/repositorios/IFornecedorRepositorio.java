package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entidades.Fornecedor;


@Repository
public interface IFornecedorRepositorio extends JpaRepository<Fornecedor, Integer> {
	
}
