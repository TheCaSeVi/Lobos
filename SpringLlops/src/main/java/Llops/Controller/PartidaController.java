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
		Set<Vot> votsPartidaReturn = new HashSet<Vot>();

		for (Vot vot : votsPartida) {
			if (vot.getTornVot() == torn) {
				votsPartidaReturn.add(vot);
			}
		}

		return votsPartidaReturn;

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
	private Iterable<User> jugadorsVius(@RequestParam int idPartida) {

		Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);

		if (!partidaOptional.isPresent())
			return null;

		Partida partida = partidaOptional.get();

		Set<RolJugadorPartida> rolJugadorPartida = partida.getUsersRolsPartida();

		Set<User> vius = new HashSet<User>();

		for (RolJugadorPartida user : rolJugadorPartida) {
			if (user.getPartida().getIdPartida() == idPartida && !user.getMort()) {

				vius.add(user.getUser());

			}
		}

		return vius;

	}

	@GetMapping(path = "/rolsVius")
	@ResponseBody
	private Iterable<Rol> rolsVius(@RequestParam int idPartida) {

		Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);

		if (!partidaOptional.isPresent())
			return null;

		Partida partida = partidaOptional.get();

		Set<RolJugadorPartida> rolJugadorPartida = partida.getUsersRolsPartida();

		Set<Rol> rols = new HashSet<Rol>();

		for (RolJugadorPartida user : rolJugadorPartida) {
			if (user.getPartida().getIdPartida() == idPartida) {
				rols.add(user.getRol());
			}
		}

		return rols;

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
	private Iterable<XatMessage> getXatLlops(@RequestParam int idPartida) {

		Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);

		if (!partidaOptional.isPresent())
			return null;

		Partida partida = partidaOptional.get();

		Set<XatMessage> xat = partida.getPartidaXat();

		Set<XatMessage> xatLlops = new HashSet<XatMessage>();

		for (XatMessage xatMessage : xat) {
			User userXat = xatMessage.getSenderXatMessage();
			Set<RolJugadorPartida> rolsjugador = userXat.getUsersRolsPartida();
			for (RolJugadorPartida rolJugadorPartida : rolsjugador) {
				if (rolJugadorPartida.getPartida().equals(partida)
						&& "llop".equalsIgnoreCase(rolJugadorPartida.getRol().getNom())) {
					xatLlops.add(xatMessage);
					break;
				}
			}
		}

		return xatLlops;

	}

}
