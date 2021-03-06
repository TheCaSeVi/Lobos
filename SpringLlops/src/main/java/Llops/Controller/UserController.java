package Llops.Controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Llops.DataBase.*;
import Llops.Repos.MessageRepository;
import Llops.Repos.PartidaRepository;
import Llops.Repos.UserRepository;
import Llops.Repos.VotRepository;
import Llops.Repos.XatMessageRepository;

@Controller
@RequestMapping(path = "/llops/user")
public class UserController {

	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PartidaRepository partidaRepository;
	@Autowired
	private VotRepository votRepository;
	@Autowired
	private XatMessageRepository xatMessageRepository;

	@GetMapping(path = "/getMissatges")
	@ResponseBody
	private Iterable<Message> getMissatges(@RequestParam String userName) {

		Optional<User> userOptional = userRepository.findById(userName);

		if (!userOptional.isPresent())
			return null;

		User user = userOptional.get();

		Set<Message> messagesSends = user.getSendsMessage();

		Set<Message> messagesRecives = user.getRecivesMessage();

		messagesSends.addAll(messagesRecives);

		return messagesSends;
	}

	@GetMapping(path = "/registre")
	@ResponseBody
	private String registre(@RequestParam String userName, @RequestParam String password, @RequestParam String alias,
			@RequestParam String pathAvatar) throws NoSuchAlgorithmException {

		Optional<User> optUser = userRepository.findById(userName);

		if (optUser.isPresent())
			return "Aquest nom d'usuari ja existeix";
		else {

			String hashPass = hashPass(userName, password);
			User user = new User(userName, hashPass, alias, pathAvatar);
			userRepository.save(user);
			optUser = userRepository.findById(userName);
			if (optUser.isPresent())
				return "L'usuari s'ha registrat correctament";
			else
				return "L'usuari no es pot registrar";

		}

	}

	@GetMapping(path = "/login")
	@ResponseBody
	private boolean login(@RequestParam String userName, @RequestParam String password)
			throws NoSuchAlgorithmException {

		Optional<User> optUser = userRepository.findById(userName);

		if (optUser.isPresent()) {

			User user = optUser.get();

			String hashPass = hashPass(userName, password);

			if (userName.equals(user.getUserName()) && hashPass.equals(user.getPassword()))
				return true;
			else
				return false;

		} else
			return false;

	}

	@GetMapping(path = "/unirse")
	@ResponseBody
	private String unirse(@RequestParam int idPartida, @RequestParam String userName) {

		String string = "";

		Optional<User> userOptional = userRepository.findById(userName);

		Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);

		if (!userOptional.isPresent())
			return "No s'ha trobat l'usuari";

		if (!partidaOptional.isPresent())
			return "No s'ha trobat la partida";

		User user = userOptional.get();

		Partida partida = partidaOptional.get();

		Set<User> usersPartida = partida.getUsers();

		Set<Partida> partidesUser = user.getPartides();

		if (usersPartida.size() == 0) {

			usersPartida.add(user);
			partida.setUsers(usersPartida);
			partidaRepository.save(partida);

			partidesUser.add(partida);
			user.setPartides(partidesUser);
			userRepository.save(user);

			string = "T'has pogut unir d'una forma molt bonica";

		} else {

			Boolean estaPartida = false;

			for (User userPartida : usersPartida) {

				if (userPartida.equals(user)) {

					estaPartida = true;
					string = "Ja estas dins de la partida";

				}

			}

			if (!estaPartida) {

				usersPartida.add(user);
				partida.setUsers(usersPartida);
				partidaRepository.save(partida);

				partidesUser.add(partida);
				user.setPartides(partidesUser);
				userRepository.save(user);

				string = "T'has pogut unir d'una forma molt bonica";

			}

		}

		return string;

	}

	@GetMapping(path = "/vota")
	@ResponseBody
	private String vota(@RequestParam String usernameSender, @RequestParam String usernameReceiver,
			@RequestParam int idPartida, @RequestParam int torn) { // Este metodo está mal, hay que cambiarlo

		Optional<User> senderOptional = userRepository.findById(usernameSender);

		Optional<User> reciverOptional = userRepository.findById(usernameReceiver);

		Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);

		if (!senderOptional.isPresent())
			return "No s'ha trobat l'usuari que l'envia";

		if (!reciverOptional.isPresent())
			return "No s'ha trobat l'usuari que el rep";

		if (!partidaOptional.isPresent())
			return "No s'ha trobat la partida";

		User sender = senderOptional.get();

		User reciver = reciverOptional.get();

		Partida partida = partidaOptional.get();

		try {
			Vot vot = new Vot(sender, reciver, partida, torn);

			Set<Vot> votsSender = sender.getSendsVots();
			votsSender.add(vot);

			Set<Vot> votsReciver = reciver.getRecivesVots();
			votsReciver.add(vot);

			votRepository.save(vot);

			sender.setSendsVots(votsSender);

			reciver.setRecivesVots(votsReciver);

			userRepository.save(sender);
			userRepository.save(reciver);

			Set<Vot> votsPartida = partida.getVotsPartida();

			votsPartida.add(vot);

			partida.setVotsPartida(votsPartida);

			partidaRepository.save(partida);

			return "S'ha desat correctament el vot";

		} catch (Exception e) {

			return "No s'ha pogut guardar el vot";

		}

	}

	@GetMapping(path = "/descobrirRol")
	@ResponseBody
	private String descobrirRol(@RequestParam String userName, @RequestParam int idPartida) {

		Optional<User> userOptional = userRepository.findById(userName);

		Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);

		if (!userOptional.isPresent())
			return "No s'ha trobat l'usuari";

		if (!partidaOptional.isPresent())
			return "No s'ha trobat la partida";

		User user = userOptional.get();

		Partida partida = partidaOptional.get();

		Set<RolJugadorPartida> rolsPartia = user.getUsersRolsPartida();

		for (RolJugadorPartida rolJugadorPartida : rolsPartia) {

			Partida p = rolJugadorPartida.getPartida();
			User u = rolJugadorPartida.getUser();

			if (u.equals(user) && p.equals(partida)) {

				Rol rol = rolJugadorPartida.getRol();
				return rol.toString();

			}
		}

		return null;

	}

	@GetMapping(path = "/enviaMissatge")
	@ResponseBody
	private String enviaMissatge(@RequestParam String content, @RequestParam String usernameSender,
			@RequestParam String usernameReceiver, @RequestParam TypeMessage type) {

		Optional<User> senderOptional = userRepository.findById(usernameSender);

		Optional<User> reciverOptional = userRepository.findById(usernameReceiver);

		if (!senderOptional.isPresent())
			return "No s'ha trobat l'usuari que l'envia";

		if (!reciverOptional.isPresent())
			return "No s'ha trobat l'usuari que el rep";

		try {

			User sender = senderOptional.get();

			User reciver = reciverOptional.get();

			Date date = new Date(System.currentTimeMillis());

			Message newMessage = new Message(sender, reciver, content, date);

			newMessage.setType(type);

			Set<Message> messagesSender = sender.getSendsMessage();
			Set<Message> messagesReciver = reciver.getRecivesMessage();

			messagesReciver.add(newMessage);
			messagesSender.add(newMessage);

			reciver.setRecivesMessage(messagesReciver);
			sender.setSendsMessage(messagesSender);

			userRepository.save(sender);
			userRepository.save(reciver);

			messageRepository.save(newMessage);

			return "S'ha guardat el missatge";

		} catch (Exception e) {
			return "No s'ha guardat el missatge";
		}

	}

	@GetMapping(path = "/escriuMissatgeXat")
	@ResponseBody
	private String escriuMissatgeXat(@RequestParam String message, @RequestParam String idSender,
			@RequestParam int idPartida) {

		Optional<User> senderOptional = userRepository.findById(idSender);

		Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);

		if (!senderOptional.isPresent())
			return "No s'ha trobat l'usuari que l'envia";

		if (!partidaOptional.isPresent())
			return "No s'ha trobat la partida";

		User sender = senderOptional.get();

		Partida partida = partidaOptional.get();

		if (partida.getTorn() % 2 == 0) {

			try {
				Set<XatMessage> xatSenderMessages = sender.getSendsXatMessage();

				Set<XatMessage> xatPartida = partida.getPartidaXat();

				Date date = new Date(System.currentTimeMillis());

				XatMessage x = new XatMessage(sender, partida, message, date);

				xatPartida.add(x);

				partida.setPartidaXat(xatPartida);

				partidaRepository.save(partida);

				xatMessageRepository.save(x);

				xatSenderMessages.add(x);

				sender.setSendsXatMessage(xatSenderMessages);

				userRepository.save(sender);

				return "S'ha guardat el missatge";
			} catch (Exception e) {

				return "No s'ha pogut escriur el missatge";

			}
		} else
			return "No s'ha pogut escriur el missatge";
	}

	@GetMapping(path = "/escriuMissatgeLlop")
	@ResponseBody
	private String escriuMissatgeLlop(@RequestParam String message, @RequestParam String idSender,
			@RequestParam int idPartida) {

		Optional<User> senderOptional = userRepository.findById(idSender);

		if (!senderOptional.isPresent())
			return "N'ho s'ha trobat l'usuari que l'envia";

		Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);

		if (!partidaOptional.isPresent())
			return "No s'ha trobat la partida";

		User sender = senderOptional.get();

		Partida partida = partidaOptional.get();

		Set<XatMessage> xatPartida = partida.getPartidaXat();
		Set<RolJugadorPartida> rolsPartida = sender.getUsersRolsPartida();
		Set<XatMessage> xatsSender = sender.getSendsXatMessage();

		// return in this for
		for (RolJugadorPartida rolJugadorPartida : rolsPartida) {

			Partida pa = rolJugadorPartida.getPartida();
			User u = rolJugadorPartida.getUser();

			if (u.equals(sender) && pa.equals(partida)) {

				Rol rol = rolJugadorPartida.getRol();

				if (rol.getNom().equals("Llop") && partida.getTorn() % 2 != 0) {

					Date date = new Date(System.currentTimeMillis());

					XatMessage x = new XatMessage(sender, partida, message, date);

					xatPartida.add(x);
					partida.setPartidaXat(xatPartida);
					partidaRepository.save(partida);

					xatMessageRepository.save(x);

					xatsSender.add(x);
					sender.setSendsXatMessage(xatsSender);
					userRepository.save(sender);

					return "S'ha enviat el missatge";

				}

			}
		}

		return "No s'ha pogut enviar el missatge";

	}

	private String hashPass(String userName, String password) throws NoSuchAlgorithmException {

		String passForHash = password + userName;

		MessageDigest md = MessageDigest.getInstance("SHA1");

		byte[] messageDigest = md.digest(passForHash.getBytes());

		BigInteger bg = new BigInteger(1, messageDigest);

		String hashPass = bg.toString(16);

		while (hashPass.length() < 32) {

			hashPass = "0" + hashPass;

		}
		return hashPass;
	}

}
