package gui;

import java.util.List;
import java.util.Scanner;

import model.Ordenador;
import model.Persona;
import service.OrdenadorService;
import service.OrdenadorServiceImpl;

public class OrdenadorGui {
	public static final int AGREGAR = 0;
	public static final int MODIFICAR = 1;
	private Scanner scanner;
	private OrdenadorService ordenadorService;
	
	public OrdenadorGui() {
		this.scanner = new Scanner(System.in);
		this.ordenadorService = new OrdenadorServiceImpl();
	}
	
	public void iniciar() {
		while (true) {
			System.out.println();
			System.out.println("1. Agregar");
			System.out.println("2. Modificar");
			System.out.println("3. Eliminar");
			System.out.println("4. Listar");
			System.out.println("5. Salir");
			String opcion = scanner.nextLine();
			
			if (opcion.equals("1"))
				guardar(AGREGAR);
			else if (opcion.equals("2"))
				guardar(MODIFICAR);
			else if (opcion.equals("3"))
				eliminar();
			else if (opcion.equals("4"))
				listar();
			else if (opcion.equals("5"))
				break;
		}
	}
	
	private void listar() {
		List<Ordenador> ordenadores = ordenadorService.obtenerOrdenadores();
		if (ordenadores != null) for (Ordenador o : ordenadores) {
			System.out.println(o);
		}
	}

	private void eliminar() {
		System.out.print("Id? ");
		Integer id = Integer.parseInt(scanner.nextLine());
		ordenadorService.eliminarOrdenador(id);
	}

	private void guardar(int op) {
		Integer id = null;
		if (op == MODIFICAR) {
			System.out.print("Id? ");
			id = Integer.parseInt(scanner.nextLine());
		}
		
		System.out.print("Nombre? ");
		String nombre = scanner.nextLine();
		
		System.out.print("Serial? ");
		String serial = scanner.nextLine();

		System.out.print("Dueño (id)? ");
		Integer personaId = Integer.parseInt(scanner.nextLine());
		
		Ordenador o = new Ordenador(nombre, serial, new Persona(personaId));
		
		if (op == MODIFICAR) {
			o.setId(id); 
			ordenadorService.modificarOrdenador(o);
		} else {
			ordenadorService.agregarOrdenador(o);
		}
	}

	public static void main(String[] args) {
		new OrdenadorGui().iniciar();
	}
}
