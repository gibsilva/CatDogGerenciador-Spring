package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity(name = "fornecedor")
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
	
    public Fornecedor() { }
    
    public Fornecedor(int id, String nome, String cnpj, 
    boolean ativo, String cep, String logradouro, 
    String numero, String complemento, String bairro,
    String cidade, String estado) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.ativo = ativo;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
     }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank
    private String nome;

    @Column
    @NotBlank
    @Size(min = 14,max = 14)
    private String cnpj;

    @Column
    @NotBlank
    private boolean ativo;

    @Column
    @NotBlank
    private String cep;

    @Column
    @NotBlank
    private String logradouro;

    @Column
    @NotBlank
    private String numero;

    @Column
    @NotBlank
    private String complemento;

    @Column
    @NotBlank
    private String bairro;

    @Column
    @NotBlank
    private String cidade;

    @Column
    @NotBlank
    private String estado;
}