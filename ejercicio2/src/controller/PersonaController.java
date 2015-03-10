package controller;

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
	
	@RequestMapping("mostrar") 
	public String mostrar(Model model) {
		model.addAttribute("personas", personaService.obtenerPersonas());
		return "/WEB-INF/jsp/persona/mostrar.jsp";
	}

	// @RequestMapping("resultado")
	// public String resultado(
	// @RequestParam String nombre,
	// @RequestParam String apellido,
	// @RequestParam Integer edad,
	// @RequestParam String btnAgregar,
	// Model model) {
	//
	// model.addAttribute("nombre", nombre);
	// model.addAttribute("apellido", apellido);
	// model.addAttribute("edad", edad);
	//
	// if (btnAgregar != null)
	// model.addAttribute("mensaje", "Agregando...");
	// else
	// model.addAttribute("mensaje", "Modificando...");
	//
	// return "/jsp/persona/resultado.jsp";
	// }

	@RequestMapping("resultado")
	public String resultado(PersonaForm p, @RequestParam String btnAgregar,
			Model model) {

		model.addAttribute("persona", p);

		if (btnAgregar != null)
			model.addAttribute("mensaje", "Agregando...");
		else
			model.addAttribute("mensaje", "Modificando...");

		return "/WEB-INF/jsp/persona/resultado.jsp";
	}
}
