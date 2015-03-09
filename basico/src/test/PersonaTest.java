package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import model.Persona;

public class PersonaTest {
	private EntityManager entityManager; // Envuelve la sesión de Hibernate!
	
	public PersonaTest() {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("persona-jpa"); // Nombre definido en persistence.xml
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	public void iniciar() throws Exception {
		agregar();
		System.out.println(obtenerTodos());
		modificar();
		System.out.println(obtenerTodos());
		eliminar();
		System.out.println(obtenerTodos());
	}
	
	@SuppressWarnings("unchecked")
	private List<Persona> obtenerTodos() {
		return entityManager.createNamedQuery("Persona.findAll")
				.getResultList();
	}

	private void eliminar() {
		EntityTransaction tx = entityManager.getTransaction();
		
		try {
			tx.begin();
			Persona p = entityManager.find(Persona.class, 3); // WHERE id = 3
			entityManager.remove(p); // Debe ser un objeto persistente 
									// por eso el find!!!
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	private void modificar() {
		EntityTransaction tx = entityManager.getTransaction();
		
		try {
			tx.begin();
			Persona p = new Persona("Juanito", "Pérez", null);
			p.setId(1);
			entityManager.merge(p);
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	private void agregar() throws Exception {
		Date fnac = new SimpleDateFormat("yyyy-MM-dd").parse("1980-02-02");
		EntityTransaction tx = entityManager.getTransaction();
		
		try {
			tx.begin();
			entityManager.persist(new Persona("Juan", "Pérez", fnac));
			entityManager.persist(new Persona("María", "García", fnac));
			entityManager.persist(new Persona("Pedro", "González", fnac));
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		entityManager.close();
	}
	
	public static void main(String[] args) throws Exception {
		new PersonaTest().iniciar();
	}
}
