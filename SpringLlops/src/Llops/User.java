package Llops;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable{


	
	@Id
	@Column(name = "id_user")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "alias")
	private String alias;
		
	@Column(name = "path_avatar")
	private String pathAvatar;	
	
	@Column(name = "percentatge_vict")
	private double percentatgeVict;
	
	@OneToMany(mappedBy="senderMessage") 	
	private Set<Message> sendsMessage = new HashSet<Message>();
	
	@OneToMany(mappedBy="reciveMessage")
	private Set<Message> recivesMessage = new HashSet<Message>();
	
	@OneToMany(mappedBy="senderVot")
	private Set<Vot> sendsVots = new HashSet<Vot>();
	
	@OneToMany(mappedBy="partidVot")
	private Set<Vot> recivesVots = new HashSet<Vot>();
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(joinColumns={@JoinColumn(name="userName")}, inverseJoinColumns={@JoinColumn(name="idPartida")})
	private Set<Partida> partides = new HashSet<>();
    
	@OneToMany(mappedBy="idUserRolPartida")
	private Set<RolJugadorPartida>usersRolsPartida = new HashSet<RolJugadorPartida>();
	
	@OneToMany(mappedBy="senderXatMessage")
	private Set<XatMessage> sendsXatMessage = new HashSet<XatMessage>();

	@OneToMany(mappedBy="userMort")
	private Set<Mort> userMorts = new HashSet<Mort>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userName,String password) {
		super();
		this.userName = userName;
		this.password=password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPathAvatar() {
		return pathAvatar;
	}

	public void setPathAvatar(String pathAvatar) {
		this.pathAvatar = pathAvatar;
	}

	public double getPercentatgeVict() {
		return percentatgeVict;
	}

	public void setPercentatgeVict(double percentatgeVict) {
		this.percentatgeVict = percentatgeVict;
	}

	public Set<Message> getSendsMessage() {
		return sendsMessage;
	}

	public void setSendsMessage(Set<Message> sendsMessage) {
		this.sendsMessage = sendsMessage;
	}

	public Set<Message> getRecivesMessage() {
		return recivesMessage;
	}

	public void setRecivesMessage(Set<Message> recivesMessage) {
		this.recivesMessage = recivesMessage;
	}

	public Set<Vot> getSendsVots() {
		return sendsVots;
	}

	public void setSendsVots(Set<Vot> sendsVots) {
		this.sendsVots = sendsVots;
	}

	public Set<Vot> getRecivesVots() {
		return recivesVots;
	}

	public void setRecivesVots(Set<Vot> recivesVots) {
		this.recivesVots = recivesVots;
	}

	public Set<Partida> getPartides() {
		return partides;
	}

	public void setPartides(Set<Partida> partides) {
		this.partides = partides;
	}

	public Set<RolJugadorPartida> getUsersRolsPartida() {
		return usersRolsPartida;
	}

	public void setUsersRolsPartida(Set<RolJugadorPartida> usersRolsPartida) {
		this.usersRolsPartida = usersRolsPartida;
	}

	public Set<XatMessage> getSendsXatMessage() {
		return sendsXatMessage;
	}

	public void setSendsXatMessage(Set<XatMessage> sendsXatMessage) {
		this.sendsXatMessage = sendsXatMessage;
	}

	public Set<Mort> getUserMorts() {
		return userMorts;
	}

	public void setUserMorts(Set<Mort> userMorts) {
		this.userMorts = userMorts;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", alias=" + alias + ", pathAvatar=" + pathAvatar + ", percentatgeVict="
				+ percentatgeVict + ", sendsMessage=" + sendsMessage + ", recivesMessage=" + recivesMessage
				+ ", sendsVots=" + sendsVots + ", recivesVots=" + recivesVots + ", partides=" + partides
				+ ", usersRolsPartida=" + usersRolsPartida + ", sendsXatMessage=" + sendsXatMessage + ", userMorts="
				+ userMorts + "]";
	}

	
	
}
