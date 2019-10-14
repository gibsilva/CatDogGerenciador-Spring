package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity(name = "marca")
public class Marca implements Serializable {

    private static final long serialVersionUID = 1L;

    public Marca() {
    }

    public Marca(int id, String nome, int idfornecedor, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
        this.idFornecedor = idfornecedor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "Campo Obrigatório")
    private String nome;

    @Column(name = "idfornecedor", nullable = false)
    private Integer idFornecedor;

    @Column(name = "ativo")
    private boolean ativo;

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Fornecedor fornecedor;

    @ManyToOne()
    @JoinColumn(name = "idfornecedor", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_Produto_Fornecedor_IdFornecedor"))
    public Fornecedor getFornecedor() {
        return this.fornecedor;
    }
}
