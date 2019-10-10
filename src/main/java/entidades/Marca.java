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
@Entity(name = "marca")
public class Marca implements Serializable {

    private static final long serialVersionUID = 1L;

    public Marca() {
    }

    public Marca(int id, String nome, String cnpj, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.ativo = ativo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nome", nullable=false)
    @NotBlank(message = "Nome da marca é obrigatório.")
    private String nome;

    @Column(name = "cnpj", nullable = false)
    @NotBlank(message = "Cnpj é obrigatorio")
    @Size(min=14, max=14)
    private String cnpj;

    @Column(name = "ativo")
    private boolean ativo;

    
    public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
        this.ativo = ativo;
	}


}

   
