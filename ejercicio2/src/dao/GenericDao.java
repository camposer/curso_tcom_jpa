package dao;

import java.util.List;

public interface GenericDao<TABLE, KEY> {
	public void agregar(TABLE obj);
	public void modificar(TABLE obj);
	public void eliminar(KEY id);
	public TABLE obtener(KEY id);
	public List<TABLE> obtenerTodos();
}
