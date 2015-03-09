package service;

import java.util.List;

import dao.OrdenadorDao;
import model.Ordenador;

public class OrdenadorServiceImpl implements OrdenadorService {
	private OrdenadorDao ordenadorDao;

	public OrdenadorServiceImpl() {
		this.ordenadorDao = new OrdenadorDaoImpl();
	}
	
	public void agregarOrdenador(Ordenador o) {
		ordenadorDao.agregar(o);
	}

	public void modificarOrdenador(Ordenador o) {
		ordenadorDao.modificar(o);
	}

	public void eliminarOrdenador(Integer id) {
		ordenadorDao.eliminar(id);
	}

	public Ordenador obtenerOrdenador(Integer id) {
		return ordenadorDao.obtener(id);
	}

	public List<Ordenador> obtenerOrdenadores() {
		return ordenadorDao.obtenerTodos();
	}

}
