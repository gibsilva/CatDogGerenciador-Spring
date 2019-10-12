/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import entidades.enums.ETipoPermissao;
import java.io.Serializable;
import java.util.Date;
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

/**
 *
 * @author adrianne
 */
@Data
@Entity(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    public Cliente() {
    }

    public Cliente(int id, String nome, String cpf, String email,
            Date dataNasc, ETipoPermissao permissao,
            String senha, String cep) {

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNasc = dataNasc;
        this.permissao = permissao;
        this.senha = senha;
        this.cep = cep;
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
    
    @Column(name="dataNasc", nullable=false)
    @NotBlank(message = "Campo obrigatório")
    @Size(min=10, max=10)
    private Date dataNasc;
       
    @Column(name="permissao", nullable=false)
    @NotBlank(message = "Campo obrigatório")
    private ETipoPermissao permissao;
    
    @Column(name="senha", nullable=false)
    @NotBlank(message = "Campo obrigatório")
    @Min(5)
    private String senha;
    
    @Column(name="cep", nullable=false)
    @NotBlank(message = "Campo obrigatório")
    @Size(min=8, max=8)
    private String cep;

}
