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
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
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

    @Column(name = "precoCompra", nullable = false)
    @DecimalMin("0.0")
    @NotNull
    private double precoCompra;

    @Column(name = "precoVenda", nullable = false)
    @DecimalMin("0.0")
    @NotNull
    private double precoVenda;

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

    @OneToMany(mappedBy = "produto")
    private List<Imagem> imagens;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the especificacao
     */
    public String getEspecificacao() {
        return especificacao;
    }

    /**
     * @param especificacao the especificacao to set
     */
    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    /**
     * @return the precoCompra
     */
    public double getPrecoCompra() {
        return precoCompra;
    }

    /**
     * @param precoCompra the precoCompra to set
     */
    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    /**
     * @return the precoVenda
     */
    public double getPrecoVenda() {
        return precoVenda;
    }

    /**
     * @param precoVenda the precoVenda to set
     */
    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the ativo
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the idCategoria
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
