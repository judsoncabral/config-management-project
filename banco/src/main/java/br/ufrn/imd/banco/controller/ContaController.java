package br.ufrn.imd.banco.controllers;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufrn.imd.banco.model.Conta;
import br.ufrn.imd.banco.repository.ContaRepository;

@Controller
@RequestMapping(value = "/contas")
public class ContaController {

		
		@RequestMapping(value = "/{id}", method = RequestMethod.GET)
		public String getConta (@PathVariable Long id) {
			return "conta/view";
		}
		
		@RequestMapping(value = "/criar", method = RequestMethod.POST)
		public String novaConta(@ModelAttribute Conta conta) {	
			ContaRepository.adicionar(conta);
			return "conta/list";
		}
		
		@RequestMapping(value = "/debitar", method = RequestMethod.POST)
		public String debitarConta(@ModelAttribute Conta conta, Double valor) {
			if( conta.getSaldo() >= valor) {
				conta.setSaldo(conta.getSaldo()-valor);
				ContaRepository.alterar(conta);
			}
			return "conta/list";
		}
		
		@RequestMapping(value = "/creditar", method = RequestMethod.POST)
		public String creditarConta(@ModelAttribute Conta conta, Double valor) {
			conta.setSaldo(conta.getSaldo()+valor);
			ContaRepository.alterar(conta);
			return "conta/list";
		}
		
		@RequestMapping(value = "/transferir", method = RequestMethod.POST)
		public String transferirConta(@ModelAttribute Conta contaCreditada,Conta contaDebitada , Double valor) {
			if( contaDebitada.getSaldo() >= valor) {
				
				contaDebitada.setSaldo(contaDebitada.getSaldo()-valor);
				contaCreditada.setSaldo(contaCreditada.getSaldo()+valor);
				
				ContaRepository.alterar(contaDebitada);
				ContaRepository.alterar(contaCreditada);
			}
			ContaRepository.alterar(contaCreditada);
			ContaRepository.alterar(contaDebitada);
			return "conta/list";
		}
		
		
		
		
}