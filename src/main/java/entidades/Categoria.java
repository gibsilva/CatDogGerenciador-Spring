package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity(name = "categoria")
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Categoria() { }
	
	public Categoria(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="nome", nullable=false)
    @NotBlank(message = "Campo obrigatório")
    private String nome;
}
