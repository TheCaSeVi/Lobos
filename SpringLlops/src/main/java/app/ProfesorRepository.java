package app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ProfesorRepository extends CrudRepository<Profesor, Integer> {

	  // Pots posar mètodes propis al repositori, i si fas la fòrmula de findBy els crea automàticament
	  List<Profesor> findByApe1IgnoreCase(String ape1);
	  // Si, crea els mètodes automàticament a partir del títol. Magia, ho se
	  List<Profesor> findByApe1AndApe2AllIgnoreCase(String ape1, String ape2);

	  // Pots fer un munt de burrades
	  List<Profesor> findByApe1OrderByNombreAsc(String ape1);
	  List<Profesor> findByApe1OrderByNombreDesc(String ape1);

}
