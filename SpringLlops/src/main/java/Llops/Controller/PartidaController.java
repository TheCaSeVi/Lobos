package Llops.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Llops.DataBase.*;
import Llops.Repos.*;

@Controller
@RequestMapping(path = "/llops/partida")
public class PartidaController {

	@Autowired
	private PartidaRepository partidaRepository;
	@Autowired
	private RolRepository rolRepository;

	@GetMapping(path = "/inici")
	@ResponseBody
	private void inici(@RequestParam int idPartida) {
		Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);

		if (partidaOptional.isPresent()) {

			Iterable<Rol> rols = rolRepository.findAll();

			Random rand = new Random();

			Partida partida = partidaOptional.get();

			Rol llop = new Rol();
			Rol vilata = new Rol();
			Rol angel = new Rol();
			Rol caçador = new Rol();
			Rol alcalde = new Rol();
			Rol enamorat = new Rol();

			for (Rol rol : rols) {

				if ("llop".equalsIgnoreCase(rol.getNom())) {

					llop = rol;

				}

				if ("vilata".equalsIgnoreCase(rol.getNom())) {

					vilata = rol;

				}

				if ("angel".equalsIgnoreCase(rol.getNom())) {

					angel = rol;

				}

				if ("caçador".equalsIgnoreCase(rol.getNom())) {

					caçador = rol;

				}

				if ("alcalde".equalsIgnoreCase(rol.getNom())) {

					alcalde = rol;

				}

				if ("enamorat".equalsIgnoreCase(rol.getNom())) {

					enamorat = rol;

				}

			}

			Set<RolJugadorPartida> rolJugadorPartidas = partida.getUsersRolsPartida();

			int numLlops = rolJugadorPartidas.size() / llop.getFreq();

			ArrayList<Rol> roles = new ArrayList<Rol>();

			for (int i = 0; i < numLlops; i++) {
				roles.add(llop);
			}

			if (!angel.getNom().isEmpty()) {

				for (int i = 0; i < angel.getFreq(); i++) {
					roles.add(angel);
				}

			}

			if (!alcalde.getNom().isEmpty()) {

				for (int i = 0; i < alcalde.getFreq(); i++) {
					roles.add(alcalde);
				}

			}

			if (!caçador.getNom().isEmpty()) {

				for (int i = 0; i < caçador.getFreq(); i++) {
					roles.add(caçador);
				}

			}

			if (!enamorat.getNom().isEmpty()) {

				for (int i = 0; i < enamorat.getFreq(); i++) {
					roles.add(enamorat);
				}

			}

			for (RolJugadorPartida rolJugadorPartida : rolJugadorPartidas) {

				if (roles.size() > 0) {

					int numRand = rand.nextInt(roles.size());

					rolJugadorPartida.setRol(roles.get(numRand));

					roles.remove(numRand);

				} else {

					rolJugadorPartida.setRol(vilata);

				}

			}

			partida.setUsersRolsPartida(rolJugadorPartidas);
			partidaRepository.save(partida);

		}

	}

	@GetMapping(path = "/fiTorn")
	@ResponseBody
	private void fiTorn(@RequestParam int idPartida) {

	}

	@GetMapping(path = "/getHistorial")
	@ResponseBody
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
