package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import entidades.Usuario;import java.util.List;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer> {
	public Usuario findByEmail(String email);
	
    public Usuario findByCpf(String cpf);
    
    public List<Usuario> findByAtivo(boolean ativo);
    
	@Query(value = "SELECT * FROM usuario WHERE email = :email and senha = :senha", nativeQuery = true)
	public Usuario findByEmailAndSenha(@Param("email")String email, @Param("senha")String senha);
	
	@Query(value = "SELECT * FROM usuario WHERE senha = :senha and id = :id", nativeQuery = true)
	public Usuario comparaSenha(@Param("senha")String senha, @Param("id")Integer id);
}
