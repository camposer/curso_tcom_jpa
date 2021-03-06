package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ORDENADOR database table.
 * 
 */
@Entity
@Table(name="ORDENADOR")
@NamedQuery(name="Ordenador.findAll", query="SELECT o FROM Ordenador o")
public class Ordenador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=50)
	private String nombre;

	@Column(length=50)
	private String serial;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="PERSONA_ID", nullable=false)
	private Persona persona;

	public Ordenador() {
	}

	public Ordenador(String nombre, String serial, Persona persona) {
		this.nombre = nombre;
		this.serial = serial;
		this.persona = persona;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "Ordenador [id=" + id + ", nombre=" + nombre + ", serial="
				+ serial + ", persona=" + persona + "]";
	}
}