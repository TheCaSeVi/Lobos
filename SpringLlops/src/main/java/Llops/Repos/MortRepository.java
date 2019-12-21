package Llops.Repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Llops.DataBase.Mort;
import Llops.DataBase.Partida;

public interface MortRepository  extends CrudRepository<Mort, Integer>{
	
}
