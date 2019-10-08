package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entidades.Categoria;


@Repository
public interface ICategoriaRepositorio extends JpaRepository<Categoria, Integer> {
	
}
