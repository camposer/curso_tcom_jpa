package service;

import java.util.List;

import model.Ordenador;

public interface OrdenadorService {
	public void agregarOrdenador(Ordenador o);
	public void modificarOrdenador(Ordenador o);
	public void eliminarOrdenador(Integer id);
	public Ordenador obtenerOrdenador(Integer id);
	public List<Ordenador> obtenerOrdenadores();
}
