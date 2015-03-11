package dao;

import java.util.List;

import model.Persona;

public interface PersonaDao extends GenericDao<Persona, Integer> {
	public List<Persona> obtenerOrdenadasPorFecha();
	public List<Persona> obtenerOrdenadasPorNombre();
	public List<Persona> obtenerOrdenadasPorApellido();
}
