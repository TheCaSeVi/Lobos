package Llops.DataBase;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "xat_messages")
public class XatMessage implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_xat_message")
	private int idXatMessage;

	@JoinColumn(name = "sender_xat_message", nullable = false)
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private User senderXatMessage;

	@JoinColumn(name = "partida_xat_message", nullable = false)
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private Partida partidaXatMessage;

	@Column(name = "content")
	private String contentXatMessage;

	@Column(name = "data")
	private Date dataMessage;

	public XatMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public XatMessage(User senderXatMessage, Partida partidaXatMessage, String contentXatMessage, Date dataMessage) {
		super();
		this.senderXatMessage = senderXatMessage;
		this.partidaXatMessage = partidaXatMessage;
		this.contentXatMessage = contentXatMessage;
		this.dataMessage = dataMessage;
	}

	public int getIdXatMessage() {
		return idXatMessage;
	}

	public void setIdXatMessage(int idXatMessage) {
		this.idXatMessage = idXatMessage;
	}

	public User getSenderXatMessage() {
		return senderXatMessage;
	}

	public void setSenderXatMessage(User senderXatMessage) {
		this.senderXatMessage = senderXatMessage;
	}

	public Partida getPartidaXatMessage() {
		return partidaXatMessage;
	}

	public void setPartidaXatMessage(Partida partidaXatMessage) {
		this.partidaXatMessage = partidaXatMessage;
	}

	public String getContentXatMessage() {
		return contentXatMessage;
	}

	public void setContentXatMessage(String contentXatMessage) {
		this.contentXatMessage = contentXatMessage;
	}

	public Date getDataMessage() {
		return dataMessage;
	}

	public void setDataMessage(Date dataMessage) {
		this.dataMessage = dataMessage;
	}

	@Override
	public String toString() {
		return "XatMessage [idXatMessage=" + idXatMessage + ", senderXatMessage=" + senderXatMessage.getUserName()
				+ ", partidaXatMessage=" + partidaXatMessage.getIdPartida() + ", contentXatMessage=" + contentXatMessage
				+ ", dataMessage=" + dataMessage + "]";
	}

}
