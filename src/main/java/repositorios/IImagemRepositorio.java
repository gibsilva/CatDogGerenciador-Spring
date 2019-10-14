package repositorios;

import entidades.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImagemRepositorio extends JpaRepository<Imagem, Integer>{
    
}