package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entidades.Marca;

;

@Repository
public interface IMarcaRepositorio extends JpaRepository<Marca, Integer> {
}
