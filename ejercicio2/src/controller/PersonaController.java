package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import model.Persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.PersonaService;
import form.PersonaForm;

@Controller
@RequestMapping("/persona/*")
public class PersonaController {
	@Autowired
	private PersonaService personaService;
	
	@RequestMapping("") 
	public String index(Model model) {
		inicializar(model);
		return "/WEB-INF/jsp/persona/index.jsp";
	}

	@RequestMapping("mostrar")
	// TODO Refactorizar y utillizar ModelAttribute!!
	public String mostrar(@RequestParam Integer id, Model model) {
		Persona persona = personaService.obtenerPersona(id);
		
		PersonaForm personaForm = new PersonaForm();
		personaForm.setId(persona.getId());
		personaForm.setNombre(persona.getNombre());
		personaForm.setApellido(persona.getApellido());
		String fnac = (persona.getFechanacimiento()!=null)?new SimpleDateFormat("yyyy-MM-dd")
			.format(persona.getFechanacimiento()):"";
		personaForm.setFechanacimiento(fnac);
		model.addAttribute("personaForm", personaForm);
		
		inicializar(model);
		return "/WEB-INF/jsp/persona/index.jsp";
	}

	@RequestMapping("eliminar")
	// TODO Refactorizar y utillizar ModelAttribute!!
	public String eliminar(@RequestParam Integer id, Model model) {
		List<String> errores = new ArrayList<String>();
		
		try {
			personaService.eliminarPersona(id);
		} catch (Exception e) {
			e.printStackTrace();
			errores.add("Error eliminando la Persona de BD");
		}
		
		if (errores.size() > 0) {
			model.addAttribute("errores", errores);
			
			inicializar(model);
			return "/WEB-INF/jsp/persona/index.jsp";
		} else {
			return "redirect:/mvc/persona/";
		}
	}

	private void inicializar(Model model) {
		model.addAttribute("personas", personaService.obtenerPersonasOrdPorFecha());
	}
	
	@RequestMapping("guardar")
	public String resultado(PersonaForm personaForm, 
			@RequestParam(required=false) String btnAgregar,
			Model model) throws ParseException {

		// Construyendo una persona del modelo a partir de los datos del formulario
		List<String> errores = validarForm(personaForm);

		if (errores.size() == 0) { // No hubo errores de validación
			try {
				Persona persona = new Persona();
				persona.setNombre(personaForm.getNombre());
				persona.setApellido(personaForm.getApellido());
				persona.setFechanacimiento(
							new SimpleDateFormat("yyyy-MM-dd")
								.parse(personaForm.getFechanacimiento()));
				
				if (personaForm.getId() != null) { // Modificando
					persona.setId(personaForm.getId());
					personaService.modificarPersona(persona);
				} else { // Agregando
					personaService.agregarPersona(persona);
				}
			} catch (PersistenceException e) {
				e.printStackTrace();
				errores.add("Error guardando en BD");
			}
		} 
		
		if (errores.size() > 0) {
			model.addAttribute("errores", errores);
			inicializar(model);
			
			return "forward:/WEB-INF/jsp/persona/index.jsp";
		} else {
			return "redirect:/mvc/persona/";
		}
	}

	private List<String> validarForm(PersonaForm personaForm) {
		List<String> errores = new ArrayList<String>();
		
		if (personaForm.getNombre() == null ||
				personaForm.getNombre().trim().equals(""))
			errores.add("Nombre inválido");
		
		if (personaForm.getApellido() == null ||
				personaForm.getApellido().trim().equals(""))
			errores.add("Apellido inválido");
		
		try {
			if (personaForm.getFechanacimiento() != null || 
					!personaForm.getFechanacimiento().trim().equals("")) {
				new SimpleDateFormat("yyyy-MM-dd")
						.parse(personaForm.getFechanacimiento());
			}
		} catch (ParseException e1) {
			errores.add("Fecha inválida");
		}
		
		return errores;
	}
	
}







