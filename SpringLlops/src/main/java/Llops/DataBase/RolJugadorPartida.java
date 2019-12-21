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
@Table(name = "rol_jugador_partida")
public class RolJugadorPartida implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "id_rol_jugador_partida")
	private int idUserRolPartida;
	
	@JoinColumn(name="id_user", nullable=false)
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private User user;
	
	@JoinColumn(name="id_rol", nullable=false)
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private Rol rol;
	
	@JoinColumn(name="partida", nullable=false)
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private Partida partida;
	
	@Column(name="mort")
	private boolean mort;

	public RolJugadorPartida() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RolJugadorPartida(int idUserRolPartida, User user, Rol rol, Partida partida) {
		super();
		this.idUserRolPartida = idUserRolPartida;
		this.user = user;
		this.rol = rol;
		this.partida = partida;
	}

	public int getIdUserRolPartida() {
		return idUserRolPartida;
	}

	public void setIdUserRolPartida(int idUserRolPartida) {
		this.idUserRolPartida = idUserRolPartida;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	
	
	public boolean getMort() {
		return mort;
	}

	public void setMort(boolean mort) {
		this.mort = mort;
	}

	@Override
	public String toString() {
		return String.valueOf(idUserRolPartida);
	}
	

	
}
