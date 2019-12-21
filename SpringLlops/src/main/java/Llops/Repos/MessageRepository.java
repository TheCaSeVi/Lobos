package Llops.Repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Llops.DataBase.Message;
import Llops.DataBase.User;

public interface MessageRepository  extends CrudRepository<Message, Integer>{


	
}
