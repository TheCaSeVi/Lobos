package Llops.Interfaces;

import java.util.List;

import Llops.RolJugadorPartida;
import Llops.Dao.IGenericDao;

public interface IRolJugadorPartida extends IGenericDao<RolJugadorPartida, Integer> {

	void saveOrUpdate(RolJugadorPartida m);

	RolJugadorPartida get(Integer id);

	List<RolJugadorPartida> list();

	void delete(Integer id);
}
