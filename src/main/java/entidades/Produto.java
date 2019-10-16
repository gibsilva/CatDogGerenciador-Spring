package entidades;

import entidades.enums.EPorteAnimal;
import entidades.enums.ETipoAnimal;
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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "Campo nome é obrigatório")
    private String nome;

    @Column(name = "descricao", nullable = false)
    @NotBlank(message = "Campo descrição é obrigatório")
    private String descricao;

    @Column(name = "especificacao", nullable = false)
    @NotBlank(message = "Campo especificação é obrigatório")
    private String especificacao;

    @NotNull(message = "O preço de compra é obrigatório")
    @Digits(integer = 9, fraction = 2)
    private BigDecimal precoCompra;

    @NotNull(message = "O preço de venda é obrigatório")
    @Digits(integer = 9, fraction = 2)
    private BigDecimal precoVenda;

    @Column(name = "quantidade", nullable = false)
    @NotNull
    private int quantidade;

    @NotNull
    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @Column(name = "idCategoria", nullable = false)
    private int idCategoria;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Categoria categoria;

    @Column(name = "idMarca", nullable = false)
    private int idMarca;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Marca marca;

    @OneToMany(mappedBy = "produto")
    private transient List<Imagem> imagens;

    //@Column(name="tipoanimal")
    //private int tipoAnimal;
    @Column(name = "tipoanimal")
    private ETipoAnimal eTipoAnimal;

    @Column(name = "porteanimal")
    private EPorteAnimal ePorteAnimal;

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

    public ETipoAnimal geteTipoAnimal() {
        return eTipoAnimal;
    }

    public void setETipoAnimal(ETipoAnimal eTipoAnimal) {
        this.eTipoAnimal = eTipoAnimal;
    }

    public EPorteAnimal getEPorteAnimal() {
        return ePorteAnimal;
    }

    public void setEPorteAnimal(EPorteAnimal ePorteAnimal) {
        this.ePorteAnimal = ePorteAnimal;
    }

    public int getIdMarca() {
        return this.idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }
}
