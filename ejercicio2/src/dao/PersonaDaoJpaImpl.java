package dao;

import java.util.List;

import org.springframework.stereotype.Component;

import model.Persona;

@Component
public class PersonaDaoJpaImpl 
	extends GenericDaoImpl<Persona, Integer> 
	implements PersonaDao {

	@Override
	public List<Persona> obtenerPorAnio(int anio) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
