package dao;

import java.util.List;

import model.Persona;

public interface PersonaDao extends GenericDao<Persona, Integer> {
	public List<Persona> obtenerPorAnio(int anio);
}
