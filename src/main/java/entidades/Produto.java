package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    @NotBlank(message = "Campo nome é obrigatório")
    private String nome;

    @Column(name = "descricao")
    @NotBlank(message = "Campo descrição é obrigatório")
    private String descricao;

    @Column(name = "especificacao")
    @NotBlank(message = "Campo especificação é obrigatório")
    private String especificacao;

    @Column(name = "precocompra")
    @NotNull(message = "O preço de compra é obrigatório")
    @Digits(integer = 9, fraction = 2)
    private BigDecimal precoCompra;

    @Column(name = "precovenda")
    @NotNull(message = "O preço de venda é obrigatório")
    @Digits(integer = 9, fraction = 2)
    private BigDecimal precoVenda;

    @Column(name = "quantidade")
    @NotNull(message = "A quantidade é obrigatória")
    private int quantidade;

    @Column(name = "ativo")
    private boolean ativo;

    @NotNull(message = "A categoria é obrigatória")
    @Column(name = "idcategoria")
    private int idCategoria;

	@ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Categoria categoria;

    @Column(name = "idmarca")
    @NotNull(message="A marca é obrigatória")
    private int idMarca;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Marca marca;

	@OneToMany(mappedBy = "produto")
    private transient List<Imagem> imagens;

    @Column(name="tipoanimal")
    @NotNull(message="O tipo de animal é obrigatório")
    private int tipoAnimal;
    
    @Column(name="porteanimal")
    @NotNull(message="O porte do animal é obrigatório")
    private int porteAnimal;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    public BigDecimal getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(BigDecimal precoCompra) {
        this.precoCompra = precoCompra;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdMarca() {
        return this.idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }
    
    public int getTipoAnimal() {
		return tipoAnimal;
	}

	public void setTipoAnimal(int tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}

	public int getPorteAnimal() {
		return porteAnimal;
	}

	public void setPorteAnimal(int porteAnimal) {
		this.porteAnimal = porteAnimal;
	}
	
    public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}
}
