package Llops.Repos;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import Llops.DataBase.*;
import app.User;

public interface PartidaRepository  extends CrudRepository<Partida, Integer> {
	
}
