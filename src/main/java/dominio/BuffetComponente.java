/**
 * 
 */
package dominio;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Guilherme Oliveira
 *
 */
@Entity
@Table(name="buffet_componentes")
public class BuffetComponente implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="buffet")
	private Buffet buffet;
	
	@ManyToOne
	@JoinColumn(name="componentes")
	private Componente componentes;
	
	public BuffetComponente(){}
	
	public BuffetComponente(Integer id, Buffet buffet, Componente componentes)
	{
		this.id = id;
		this.buffet = buffet;
		this.componentes = componentes;
		buffet.addBuffetToBuffeteComponente(this);
		componentes.addBuffetComponente(this);
	}

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
	 * @return the buffet
	 */
	public Buffet getBuffet() {
		return buffet;
	}

	/**
	 * @param buffet the buffet to set
	 */
	public void setBuffet(Buffet buffet) {
		this.buffet = buffet;
	}

	/**
	 * @return the componentes
	 */
	public Componente getComponentes() {
		return componentes;
	}

	/**
	 * @param componentes the componentes to set
	 */
	public void setComponentes(Componente componentes) {
		this.componentes = componentes;
	}
	
	/**
	 * desconto
	 */
	public void desconto(Float x)
	{
		this.componentes.setPreco(this.componentes.getPreco().multiply(new BigDecimal(x)).divide(new BigDecimal(100)));
	}

}