package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

	private void inicializar(Model model) {
		model.addAttribute("personas", personaService.obtenerPersonas());
	}
	
	@RequestMapping("guardar")
	public String resultado(PersonaForm personaForm, 
			@RequestParam(required=false) String btnAgregar,
			Model model) throws ParseException {

		// Construyendo una persona del modelo a partir de los datos del formulario
		Persona persona = new Persona();
		persona.setNombre(personaForm.getNombre());
		persona.setApellido(personaForm.getApellido());
		persona.setFechanacimiento(
				new SimpleDateFormat("yyyy-MM-dd")
					.parse(personaForm.getFechanacimiento()));

		boolean esValido = false;
		try {
			personaService.agregarPersona(persona);
			esValido = true;
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "Error agregando la persona");
		}

		if (!esValido) {
			inicializar(model);
			return "forward:/WEB-INF/jsp/persona/index.jsp";
		} else {
			return "redirect:/mvc/persona/";
		}
	}
}
