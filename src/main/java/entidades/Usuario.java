package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.mindrot.jbcrypt.BCrypt;

@Entity(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    public Usuario() {
    }

    public Usuario(int id, String nome, String cpf,
            String email, String permissao, String senha,
            Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.permissao = permissao;
        if (senha.length() == 60) {
            this.senha = senha;
        } else {
            setSenhaEncriptada(senha);
        }
        this.ativo = ativo;
    }

    public Usuario(int id, String nome, String cpf,
            String email, String permissao, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.permissao = permissao;
        this.ativo = ativo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @Column(name = "cpf")
    @NotBlank(message = "Cpf obrigatório")
    @Size(min = 11, max = 14)
    private String cpf;

    @Column(name = "email")
    @NotBlank(message = "Email obrigatório")
    @Email
    private String email;

    @Column(name = "permissao")
    @NotBlank(message = "Permissão obrigatória")
    private String permissao;

    @Column(name = "senha")
    @NotBlank(message = "Senha obrigatória")
    private String senha;

    @Column(name = "ativo")
    private Boolean ativo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public final void setSenhaEncriptada(String senha) {
        this.senha = BCrypt.hashpw(senha, BCrypt.gensalt(12));
    }

    public boolean validarSenha(String senha) {
        boolean senhaValida = BCrypt.checkpw(senha, this.getSenha());
        return senhaValida;
    }

}
