package vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contel")
public class Contel implements Serializable {

	private static final long serialVersionUID = 8628720230067281243L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "sq_contel_gen", sequenceName = "sq_contel", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_contel_gen")
    private BigInteger id;

    @Size(max = 10)
    @Column(name = "numero")
    private String numero;

    @Size(max = 2)
    @Column(name = "dddnum")
    private String dddnum;

    @Size(max = 250)
    @Column(name = "emails")
    private String emails;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contat", referencedColumnName = "id")
    private Contat contat;

    public Contel() {
        super();
    }

    public Contel(BigInteger id) {
        super();
        this.id = id;
    }

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
		return Objects.equals(id, other.getId());
	}
 
 
	public BigInteger getId() {
		return id;
	}
 
 
	public void setId(BigInteger id) {
		this.id = id;
	}
 
 
	public String getNumero() {
	return numero;
	}
	
	public void setNumero(String numero) {
	this.numero = numero;
	}
	
	public String getDddnum() {
		return dddnum;
	}
	
	public void setDddnum(String dddnum) {
		this.dddnum = dddnum;
	}
	
	public String getEmails() {
		return emails;
	}
	
	public void setEmails(String emails) {
		this.emails = emails;
	}
	
    public Contat getContat() {
		return contat;
	}

	public void setContat(Contat contat) {
		this.contat = contat;
	}
 
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
 
}
