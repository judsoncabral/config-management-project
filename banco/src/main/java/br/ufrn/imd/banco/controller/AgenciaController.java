package br.ufrn.imd.banco.controllers;
import br.ufrn.imd.banco.helper.ObjetoExistenteException;
import br.ufrn.imd.banco.helper.ObjetoInexistenteException;
import br.ufrn.imd.banco.helper.ObjetoVazioException;
import br.ufrn.imd.banco.model.Agencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufrn.imd.banco.repository.AgenciaRepository;

@Controller
@RequestMapping(value = "/agencias")
public class AgenciaController {
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getConta (@PathVariable Long id) {
		try {
			Agencia agencia = AgenciaRepository.buscar(id);
		} catch(ObjetoVazioException e) {
			
		} catch(ObjetoInexistenteException e) {
			
		}
		return "conta/view";
	}
	
	@RequestMapping(value = "/criar", method = RequestMethod.POST)
	public String novaAgencia(@ModelAttribute Agencia agencia) {
		try {
			AgenciaRepository.adicionar(agencia);
		} catch(ObjetoExistenteException e) {
		// --- O que fazer quando o objeto já existe? ---	
		}
		return "agencia/list";
	}
}