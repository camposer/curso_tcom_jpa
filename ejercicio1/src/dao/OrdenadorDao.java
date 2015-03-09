package dao;

import java.util.List;

import model.Ordenador;

public interface OrdenadorDao {
	public void agregar(Ordenador o);
	public void modificar(Ordenador o);
	public void eliminar(Integer id);
	public Ordenador obtener(Integer id);
	public List<Ordenador> obtenerTodos();
}
