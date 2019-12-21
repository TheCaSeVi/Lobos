package Llops.Dao;

import java.io.Serializable;
import java.util.List;

//interficie del DAO generico.  El primer tipo generico es T, que dpeendera del DAO, el segundo es una ID serializable, generalmente un Integer
public interface IGenericDao<T, ID extends Serializable> {

	//en un DAO al menos estan los cuatro metodos CRUD basicos
	//Create
	//Read o Retrieve
	//Update
	//Delete
	//en este ponemos un list que devuelva todo porque es util
	
	// en Save o Update debes pasarle el mismo tipo que el Generico que has
	// especificado en el operador Diamante
	void saveOrUpdate(T entity);

	// get devuelve el generico especificado
	T get(ID id);

	//delete le pasas la id
	void delete(ID id);

	//puedes especificar que devuelva una lista de genericos
	List<T> list();
}
