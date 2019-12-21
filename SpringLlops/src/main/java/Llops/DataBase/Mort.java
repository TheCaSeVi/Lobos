package Llops.DataBase;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mort")
public class Mort implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mort")
	private int idMort;

	@JoinColumn(name = "user_mort", nullable = false)
	@ManyToOne(cascade = CascadeType.ALL)
	private User userMort;

	@JoinColumn(name = "partida_morts", nullable = false)
	@ManyToOne(cascade = CascadeType.ALL)
	private Partida partidaMort;

	@Column(name = "torn_mort")
	private int tornMort;

	public Mort() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mort(int idMort, User userMort, Partida partidaMort, int tornMort) {
		super();
		this.idMort = idMort;
		this.userMort = userMort;
		this.partidaMort = partidaMort;
		this.tornMort = tornMort;
	}

	public int getIdMort() {
		return idMort;
	}

	public void setIdMort(int idMort) {
		this.idMort = idMort;
	}

	public User getUserMort() {
		return userMort;
	}

	public void setUserMort(User userMort) {
		this.userMort = userMort;
	}

	public Partida getPartidaMort() {
		return partidaMort;
	}

	public void setPartidaMort(Partida partidaMort) {
		this.partidaMort = partidaMort;
	}

	public int getTornMort() {
		return tornMort;
	}

	public void setTornMort(int tornMort) {
		this.tornMort = tornMort;
	}

	@Override
	public String toString() {
		return "Mort [idMort=" + idMort + ", userMort=" + userMort + ", partidaMort=" + partidaMort + ", tornMort="
				+ tornMort + "]";
	}

}
