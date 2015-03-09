package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PERSONA database table.
 * 
 */
@Entity
@Table(name="PERSONA")
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=20)
	private String apellido;

	@Temporal(TemporalType.DATE)
	private Date fechanacimiento;

	@Column(nullable=false, length=20)
	private String nombre;

	//bi-directional many-to-one association to Ordenador
	@OneToMany(mappedBy="persona")
	private List<Ordenador> ordenadores;

	public Persona(String nombre, String apellido, Date fechaNacimiento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechanacimiento = fechaNacimiento;
	}

	public Persona() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Ordenador> getOrdenadores() {
		return this.ordenadores;
	}

	public void setOrdenadores(List<Ordenador> ordenadores) {
		this.ordenadores = ordenadores;
	}

	public Ordenador addOrdenadore(Ordenador ordenadore) {
		getOrdenadores().add(ordenadore);
		ordenadore.setPersona(this);

		return ordenadore;
	}

	public Ordenador removeOrdenadore(Ordenador ordenadore) {
		getOrdenadores().remove(ordenadore);
		ordenadore.setPersona(null);

		return ordenadore;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", apellido=" + apellido
				+ ", fechanacimiento=" + fechanacimiento + ", nombre=" + nombre
				+ ", ordenadores=" + ordenadores + "]";
	}
}