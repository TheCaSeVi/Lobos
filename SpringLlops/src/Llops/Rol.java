package Llops;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "rol")
public class Rol implements Serializable{

	@Id
	@Column(name = "id_rol")
	private String nom;
	
	@Column(name = "freq", nullable = false)
	private int freq;
	
	@Column(name = "pathImg")
	private String pathImg;
	
	
	@Lob
	@Column(name = "descripcio",nullable = false)
	private String descripcio;

	@OneToMany(mappedBy="idUserRolPartida")
	private Set<RolJugadorPartida>usersRolsPartida = new HashSet<RolJugadorPartida>();
	
	public Rol() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rol(String nom, int freq, String pathImg, String descripcio, Set<RolJugadorPartida> usersRolsPartida) {
		super();
		this.nom = nom;
		this.freq = freq;
		this.pathImg = pathImg;
		this.descripcio = descripcio;
		this.usersRolsPartida = usersRolsPartida;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public String getPathImg() {
		return pathImg;
	}

	public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public Set<RolJugadorPartida> getUsersRolsPartida() {
		return usersRolsPartida;
	}

	public void setUsersRolsPartida(Set<RolJugadorPartida> usersRolsPartida) {
		this.usersRolsPartida = usersRolsPartida;
	}

	@Override
	public String toString() {
		return "Rol [nom=" + nom + ", freq=" + freq + ", pathImg=" + pathImg + ", descripcio=" + descripcio
				+ ", usersRolsPartida=" + usersRolsPartida + "]";
	}
	


}
