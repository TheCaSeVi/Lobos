package Llops.Repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Llops.DataBase.Partida;
import Llops.DataBase.XatMessage;

public interface XatMessageRepository  extends CrudRepository<XatMessage, Integer>{

	
}
