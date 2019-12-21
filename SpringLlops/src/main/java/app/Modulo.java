package app;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="Modulo")
public class Modulo implements Serializable {
    
    @Id
    @Column(name="IdModulo",updatable = true, nullable = false)    
    private int idModulo;
    
    ///especificar que sea unique. No puede repetirse. La length es la longitud máxima de la String
    @Column(name="nombre", unique=true, length=50)
    private String nombre;
    
    @Column(name="horas_semana")
    private int horasSemana;
    
    ///Lob indica que lo guarde en un lugar grande. en el caso de mysql, un text
    @Lob
    @Column(name="programacion")
    private String programacion;
    
    //Temporal tiene varios tipos. Puedes ponerle Date (solo mes/dia), Time (solo hora/minuto), o Timestamp (todo)
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_inicio")
    private Date fechaInicio;
    
    @ManyToMany(cascade = {CascadeType.ALL},mappedBy="modulos")
    ///esto indica que no quieres que se repita en el json cortando el bucle infinito
    @JsonBackReference
    private Set<Profesor> profesores=new HashSet();
    
	//relacion manytoone. Aqui declaramos la llave foranea que basicamente es un objeto del tipo referenciado
	//en nuestro caso, de clase curso. Referenciamos su llave primaria pero ponemos el objeto entero. El nombre del objeto
    //equivale al MappedBy de la clase Curso
	@ManyToOne
	@JoinColumn(name="curso")
	private Curso curso;

    public Modulo() {
        
    }

    public Modulo(int idModulo, Date date, String nombre, int horas) {
        this.idModulo = idModulo;
        this.fechaInicio = date;
        this.nombre = nombre;
        this.horasSemana = horas;

    }
   
    
    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(Set<Profesor> profesores) {
        this.profesores = profesores;
    }

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	
	
	public int getHorasSemana() {
		return horasSemana;
	}

	public void setHorasSemana(int horasSemana) {
		this.horasSemana = horasSemana;
	}

	public String getProgramacion() {
		return programacion;
	}

	public void setProgramacion(String programacion) {
		this.programacion = programacion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		//cada clase debe tener un result distinto
		int result = 444;
		result = prime * result + idModulo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Modulo))
			return false;
		Modulo other = (Modulo) obj;
		if (idModulo != other.idModulo)
			return false;
		return true;
	}
    
    
}
