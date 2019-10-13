package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entidades.Usuario;import java.util.List;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer> {
	public Usuario findByEmail(String email);
        public Usuario findByCpf(String cpf);
        public List<Usuario> findByAtivo(boolean ativo);
}
