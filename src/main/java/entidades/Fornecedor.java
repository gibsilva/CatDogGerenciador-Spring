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

    public Fornecedor() {
    }

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

    @Column(name = "nome")
    @NotBlank(message = "Campo obrigatório")
    @Size(min = 5, max = 150)
    private String nome;

    @Column(name = "cnpj")
    @NotBlank(message = "Campo obrigatório")
    @Size(min = 14, max = 14, message = "O campo deve ter {max} caracteres")
    private String cnpj;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "cep")
    @NotBlank(message = "Campo obrigatório")
    @Size(min = 8, max = 8, message = "O campo deve ter {max} caracteres")
    private String cep;

    @Column(name = "logradouro")
    @NotBlank(message = "Campo obrigatório")
    @Size(min = 2, max = 80, message = "O campo deve ter entre {min} e {max} caracteres")
    private String logradouro;

    @Column(name = "numero")
    @NotBlank(message = "Campo obrigatório")
    private String numero;

    @Column
    @NotBlank(message = "Campo obrigatório")
    private String complemento;

    @Column
    @NotBlank(message = "Campo obrigatório")
    private String bairro;

    @Column
    @NotBlank(message = "Campo obrigatório")
    private String cidade;

    @Column
    @NotBlank(message = "Campo obrigatório")
    private String estado;
    
    public void setAtivo(boolean ativo){
        this.ativo = ativo;
    }
}
