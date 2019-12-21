package Llops.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonBackReference;

import Llops.DataBase.*;
import Llops.Repos.*;

@Controller
@RequestMapping(path = "/llops/partida")
public class PartidaController {

	@Autowired
	private VotRepository votRepo;
	@Autowired
	private PartidaRepository partidaRepository;
	@Autowired
	private XatMessageRepository xatMessageRepository;

	// inici

	// fi torn

	@GetMapping(path = "/getHistorial")
	@ResponseBody
	//
	private Iterable<Vot> getHistorial(@RequestParam int idPartida, @RequestParam int torn) {
		Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);

		if (!partidaOptional.isPresent())
			return null;

		Partida partida = partidaOptional.get();

		Iterable<Vot> votsPartida = partida.getVotsPartida();

		return votsPartida;

	}

	@GetMapping(path = "/getXat")
	@ResponseBody
	private Iterable<XatMessage> getXat(@RequestParam int idPartida) {

		Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);

		if (!partidaOptional.isPresent())
			return null;

		Partida partida = partidaOptional.get();

		Iterable<XatMessage> messages = partida.getPartidaXat();

		return messages;
	}

	@GetMapping(path = "/jugadorsVius")
	@ResponseBody
	private ArrayList<String> jugadorsVius(@RequestParam int idPartida) {

		Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);

		if (!partidaOptional.isPresent())
			return null;

		Partida partida = partidaOptional.get();

		Set<Mort> morts = partida.getMortsPartida();

		Set<User> users = partida.getUsers();

		Set<User> vius = new HashSet<User>();

		for (User user : users) {
			for (Mort mort : morts) {
				if (!user.getUserName().equals(mort.getUserMort().getUserName()))
					vius.add(user);
			}
		}

		ArrayList<String> jVius = new ArrayList<String>();

		for (User jvius : vius) {
			jVius.add(jvius.toString());
		}

		return jVius;
	}

	@GetMapping(path = "/rolsVius")
	@ResponseBody
	private ArrayList<String> rolsVius(@RequestParam int idPartida) {

		Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);

		if (!partidaOptional.isPresent())
			return null;

		Partida partida = partidaOptional.get();

		Set<Mort> morts = partida.getMortsPartida();

		Set<User> users = partida.getUsers();

		Set<Rol> rolsVius = new HashSet<Rol>();

		for (User user : users) {
			for (Mort mort : morts) {
				if (!user.getUserName().equals(mort.getUserMort().getUserName())) {
					Set<RolJugadorPartida> rolsjugador = user.getUsersRolsPartida();

					for (RolJugadorPartida rolJugadorPartida : rolsjugador) {
						if (rolJugadorPartida.getPartida().equals(partida)) {
							rolsVius.add(rolJugadorPartida.getRol());
							break;
						}
					}
				}
			}
		}

		ArrayList<String> rVius = new ArrayList<String>();

		for (Rol rvius : rolsVius) {
			rVius.add(rvius.toString());
		}

		return rVius;

	}

	@GetMapping(path = "/getMorts")
	@ResponseBody
	private Iterable<Mort> getMorts(@RequestParam int idPartida) {

		Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);

		if (!partidaOptional.isPresent())
			return null;

		Partida partida = partidaOptional.get();

		Iterable<Mort> morts = partida.getMortsPartida();

		return morts;
	}

	@GetMapping(path = "/getXatLlops")
	@ResponseBody
	private ArrayList<String> getXatLlops(@RequestParam int idPartida) {

		Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);

		if (!partidaOptional.isPresent())
			return null;

		Partida partida = partidaOptional.get();

		Set<User> users = partida.getUsers();

		Set<XatMessage> xat = partida.getPartidaXat();

		Set<XatMessage> xatLlops = new HashSet<XatMessage>();

		for (XatMessage xatMessage : xat) {
			User userXat = xatMessage.getSenderXatMessage();
			Set<RolJugadorPartida> rolsjugador = userXat.getUsersRolsPartida();
			for (RolJugadorPartida rolJugadorPartida : rolsjugador) {
				if (rolJugadorPartida.getPartida().equals(partida)
						&& "llop".equals(rolJugadorPartida.getRol().getDescripcio())) {
					xatLlops.add(xatMessage);
					break;
				}
			}
		}

		ArrayList<String> xLlops = new ArrayList<String>();

		for (XatMessage xm : xatLlops) {
			xLlops.add(xm.toString());
		}

		return xLlops;

	}

}
