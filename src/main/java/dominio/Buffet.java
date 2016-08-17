/**
 * 
 */
package dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Guilherme Oliveira
 *
 */
@Entity
@Table(name="buffets")
public class Buffet implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="preco")
	private BigDecimal preco;
	
	@OneToMany(mappedBy="buffet")
	private List<BuffetComponente> buffetComponente; 
	
	public Buffet()
	{
		buffetComponente = new ArrayList<>();
	}
	
	public Buffet(Integer id, String nome, BigDecimal preco)
	{
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		buffetComponente = new ArrayList<>();
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
	 * @return the preco
	 */
	public BigDecimal getPreco() {
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	/**
	 * @return the buffetComponente
	 */
	public List<BuffetComponente> getBuffetComponente() {
		return buffetComponente;
	}

	/**
	 * @param buffetComponente the buffetComponente to set
	 */
	public void setBuffetComponente(List<BuffetComponente> buffetComponente) {
		this.buffetComponente = buffetComponente;
	}
	
	/**
	 * @param buffetComponente
	 */
	public void addBuffetToBuffeteComponente(BuffetComponente b)
	{
		buffetComponente.add(b);
		b.setBuffet(this);
	}
	
	/**
	 * @param buffetComponente
	 */
	public void removeBuffetComponente(BuffetComponente buffetComponente)
	{
		this.buffetComponente.remove(buffetComponente);
	}
	
	/**
	 * @return the total
	 */	
	public BigDecimal precoTotal(List<Componente> comp)
	{
		BigDecimal total = new BigDecimal("0.00");
		for(Componente c : comp)
		{
			total = total.add(c.getPreco());
		}
		return total.add(getPreco());
	}
	
	/**
	 * @return the maisCaro
	 */	
	public Componente componenteMaisCaro(List<Componente> comp)
	{
		Componente maisCaro = new Componente();
		maisCaro.setPreco(new BigDecimal("0.00"));
		
		for(Componente c : comp)
		{
			int x = c.getPreco().compareTo(maisCaro.getPreco());
			if(x > 0)
			{
				maisCaro = c;
			}
		}
		return maisCaro;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Buffet)) {
			return false;
		}
		Buffet other = (Buffet) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
}
