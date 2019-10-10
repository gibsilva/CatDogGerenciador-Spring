package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
@Entity(name = "usuario")
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;

    public Usuario() { }

    public Usuario(int id, String nome, String cpf, 
    String email, String permissao, String senha,
    Boolean ativo){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.permissao = permissao;
        this.senha = senha;
        this.ativo = ativo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="nome", nullable=false)
    @NotBlank(message = "Campo obrigatório")
    private String nome;
    
    @Column(name="cpf", nullable=false)
    @NotBlank(message = "Campo obrigatório")
    @Size(min=11, max=11)
    private String cpf;

    @Column(name="email", nullable=false)
    @NotBlank(message = "Campo obrigatório")
    @Email
    private String email;

    @Column(name="permissao", nullable=false)
    @NotBlank(message = "Campo obrigatório")
    private String permissao;

    @Column(name="senha", nullable=false)
    @NotBlank(message = "Campo obrigatório")
    @Min(5)
    private String senha;

    @Column(name="ativo")
    private Boolean ativo;

}