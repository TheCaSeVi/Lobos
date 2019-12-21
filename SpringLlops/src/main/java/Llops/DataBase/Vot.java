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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "vot")
public class Vot implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vot")
	private int idVot;

	@JoinColumn(name = "sernder_vot", nullable = false)
	@ManyToOne(cascade = CascadeType.ALL)
	private User senderVot;

	@JoinColumn(name = "reciver_vot", nullable = false)
	@ManyToOne(cascade = CascadeType.ALL)
	private User reciverVot;

	@JoinColumn(name = "partida_vot", nullable = false)
	@ManyToOne(cascade = CascadeType.ALL)
	private Partida partidVot;

	@Column(name = "torn_vot")
	private int tornVot;

	public Vot() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vot(User senderVot, User reciverVot, Partida partidVot, int tornVot) {
		super();
		this.senderVot = senderVot;
		this.reciverVot = reciverVot;
		this.partidVot = partidVot;
		this.tornVot = tornVot;
	}

	public int getIdVot() {
		return idVot;
	}

	public void setIdVot(int idVot) {
		this.idVot = idVot;
	}

	public User getSenderVot() {
		return senderVot;
	}

	public void setSenderVot(User senderVot) {
		this.senderVot = senderVot;
	}

	public User getReciverVot() {
		return reciverVot;
	}

	public void setReciverVot(User reciverVot) {
		this.reciverVot = reciverVot;
	}

	public Partida getPartidVot() {
		return partidVot;
	}

	public void setPartidVot(Partida partidVot) {
		this.partidVot = partidVot;
	}

	public int getTornVot() {
		return tornVot;
	}

	public void setTornVot(int tornVot) {
		this.tornVot = tornVot;
	}

	@Override
	public String toString() {
		return "Vot [idVot=" + idVot + ", senderVot=" + senderVot + ", reciverVot=" + reciverVot + ", partidVot="
				+ partidVot + ", tornVot=" + tornVot + "]";
	}

}
