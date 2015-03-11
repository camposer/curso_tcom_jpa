package dao;

import java.util.List;

import org.springframework.stereotype.Component;

import model.Persona;

@Component
public class PersonaDaoJpaImpl 
	extends GenericDaoImpl<Persona, Integer> 
	implements PersonaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> obtenerOrdenadasPorFecha() {
		String jql = "select p from Persona p order by p.fechanacimiento";
		return entityManager
				.createQuery(jql)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> obtenerOrdenadasPorNombre() {
		String sql = "select * from Persona order by fechanacimiento";
		return entityManager
				.createNativeQuery(sql, Persona.class)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> obtenerOrdenadasPorApellido() {
		return entityManager
				.createNamedQuery("Persona.findOrderedByApellido")
				.getResultList();
	}
}
