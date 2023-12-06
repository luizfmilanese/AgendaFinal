package vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;
 
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(name = "contat")
public class Contat implements Serializable {
 
		
	private static final long serialVersionUID = 8628720230067281243L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "sq_contat", sequenceName = "sq_contat", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_contat")
	private BigInteger id;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max= 100)
	@Column(name = "descri", nullable = false, length = 100)
	private String descri;

	public Contat () {
		super();	}

	public Contat (BigInteger id) {
		super();
		this.id = id; }
 
 
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
 
 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contat other = (Contat) obj;
		return Objects.equals(id, other.id);
	}
 
 
	public BigInteger getId() {
		return id;
	}
 
 
	public void setId(BigInteger id) {
		this.id = id;
	}
 
 
	public String getDescri() {
		return descri;
	}
 
 
	public void setDescri(String descri) {
		this.descri = descri;
	}
 
 
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
 
}