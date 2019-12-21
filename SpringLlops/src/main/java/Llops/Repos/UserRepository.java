package Llops.Repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Llops.DataBase.*;


public interface UserRepository extends CrudRepository<User, String>{

	User findByuserName(String username);


}
