package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entidades.Raca;


@Repository
public interface IRacaRepositorio extends JpaRepository<Raca, Integer> {
	
}
