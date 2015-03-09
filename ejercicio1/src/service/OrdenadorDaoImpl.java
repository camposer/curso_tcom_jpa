package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Ordenador;
import dao.OrdenadorDao;

public class OrdenadorDaoImpl implements OrdenadorDao {
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	
	public OrdenadorDaoImpl() {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("persona-jpa");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	@Override
	protected void finalize() throws Throwable {
		entityManager.close();
	}
	
	public void agregar(Ordenador o) {
		entityTransaction.begin();
		entityManager.persist(o);
		entityTransaction.commit();
	}

	public void modificar(Ordenador o) {
		entityTransaction.begin();
		entityManager.merge(o);
		entityTransaction.commit();
	}

	public void eliminar(Integer id) {
		entityTransaction.begin();
		Ordenador o = obtener(id);
		entityManager.remove(o);
		entityTransaction.commit();
	}

	public Ordenador obtener(Integer id) {
		return entityManager.find(Ordenador.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Ordenador> obtenerTodos() {
		return entityManager.createNamedQuery("Ordenador.findAll")
				.getResultList();
	}

}
