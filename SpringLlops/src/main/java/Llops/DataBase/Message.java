package Llops.DataBase;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "message")
public class Message implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_message", nullable = false)
	private int idMessage;

	@JoinColumn(name = "sender_message", nullable = false)
	@ManyToOne(cascade = CascadeType.ALL)
	private User senderMessage;

	@JoinColumn(name = "reciver_message", nullable = false)
	@ManyToOne(cascade = CascadeType.ALL)
	private User reciveMessage;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private TypeMessage type;

	@Lob
	@Column(name = "content", length = 50)
	private String content;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(User senderMessage, User reciveMessage, String content, Date date) {
		super();
		this.senderMessage = senderMessage;
		this.reciveMessage = reciveMessage;
		this.content = content;
		this.date = date;
	}

	public int getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public User getSenderMessage() {
		return senderMessage;
	}

	public void setSenderMessage(User senderMessage) {
		this.senderMessage = senderMessage;
	}

	public User getReciveMessage() {
		return reciveMessage;
	}

	public void setReciveMessage(User reciveMessage) {
		this.reciveMessage = reciveMessage;
	}

	public TypeMessage getType() {
		return type;
	}

	public void setType(TypeMessage type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Message [idMessage=" + idMessage + ", senderMessage=" + senderMessage + ", reciveMessage="
				+ reciveMessage + ", type=" + type + ", content=" + content + ", date=" + date + "]";
	}

}
