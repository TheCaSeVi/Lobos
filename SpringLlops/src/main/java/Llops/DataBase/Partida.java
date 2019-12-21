package Llops.DataBase;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "partida")
public class Partida implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "id_partida")
	private int idPartida;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	private Set<User> users = new HashSet<>();
	
	@Column(name = "torn")
	private int torn;
	
	@OneToMany(mappedBy="partida")
	@JsonBackReference
	private Set<RolJugadorPartida>usersRolsPartida = new HashSet<RolJugadorPartida>();
	
	@OneToMany(mappedBy="partidaXatMessage")
	private Set<XatMessage> partidaXat = new HashSet<XatMessage>();
	

	@OneToMany(mappedBy="idMort")
	@JsonBackReference
	private Set<Mort> mortsPartida = new HashSet<Mort>();
	
	@OneToMany(mappedBy="partidVot")
	@JsonBackReference
	private Set<Vot> votsPartida = new HashSet<Vot>();

	public Partida() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Partida(int idPartida, Set<User> users, int torn, Set<RolJugadorPartida> usersRolsPartida,
			Set<XatMessage> partidaXat, Set<Mort> mortsPartida, Set<Vot> votsPartida) {
		super();
		this.idPartida = idPartida;
		this.users = users;
		this.torn = torn;
		this.usersRolsPartida = usersRolsPartida;
		this.partidaXat = partidaXat;
		this.mortsPartida = mortsPartida;
		this.votsPartida = votsPartida;
	}

	public int getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public int getTorn() {
		return torn;
	}

	public void setTorn(int torn) {
		this.torn = torn;
	}

	public Set<RolJugadorPartida> getUsersRolsPartida() {
		return usersRolsPartida;
	}

	public void setUsersRolsPartida(Set<RolJugadorPartida> usersRolsPartida) {
		this.usersRolsPartida = usersRolsPartida;
	}

	public Set<XatMessage> getPartidaXat() {
		return partidaXat;
	}

	public void setPartidaXat(Set<XatMessage> partidaXat) {
		this.partidaXat = partidaXat;
	}

	public Set<Mort> getMortsPartida() {
		return mortsPartida;
	}

	public void setMortsPartida(Set<Mort> mortsPartida) {
		this.mortsPartida = mortsPartida;
	}

	public Set<Vot> getVotsPartida() {
		return votsPartida;
	}

	public void setVotsPartida(Set<Vot> votsPartida) {
		this.votsPartida = votsPartida;
	}

	@Override
	public String toString() {
		return String.valueOf(idPartida);
	}
		
	
}
