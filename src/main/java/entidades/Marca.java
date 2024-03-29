package entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
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
    @NotBlank(message = "Nome Obrigatório")
    private String nome;

    @Column(name = "idfornecedor", nullable = false)
    private Integer idFornecedor;

    @Column(name = "ativo")
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    public Fornecedor fornecedor;

    @OneToMany(mappedBy = "marca")
    List<Produto> produtos;

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

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

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
    
}
