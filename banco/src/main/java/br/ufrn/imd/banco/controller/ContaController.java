package br.ufrn.imd.banco.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufrn.imd.banco.dto.CreditarDTO;
import br.ufrn.imd.banco.dto.DebitarDTO;
import br.ufrn.imd.banco.dto.TransferenciaResponseDTO;
import br.ufrn.imd.banco.dto.TransferirDTO;
import br.ufrn.imd.banco.model.Conta;
import br.ufrn.imd.banco.repository.ContaRepository;

@Controller
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/contas")
public class ContaController {
	
		@GetMapping()
		public ResponseEntity<List<Conta>> getContas() {
			return ResponseEntity.ok(ContaRepository.contas);
		}
	
		@GetMapping(value = "/{id}")
		public ResponseEntity<Conta> getConta (@PathVariable Long id) {
			Conta conta = ContaRepository.findById(id);
			if (conta != null) {
				return ResponseEntity.ok(conta);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		@PostMapping(value = "/criar")
		public ResponseEntity<Conta> novaConta(@ModelAttribute Conta conta) {	
			ContaRepository.adicionar(conta);
			return ResponseEntity.ok(conta);
		}
		
		@PostMapping(value = "/debitar")
		public ResponseEntity<Conta> debitarConta(@RequestBody DebitarDTO debitarDTO) {
			Conta conta = ContaRepository.findById(debitarDTO.getContaId());
			if (conta != null) {
				conta.setSaldo(conta.getSaldo() - debitarDTO.getValorParaDebitar());
				ContaRepository.alterar(conta);
				return ResponseEntity.ok(conta);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		@PostMapping(value = "/creditar")
		public ResponseEntity<Conta> creditarConta(@RequestBody CreditarDTO creditarDTO) {
			Conta conta = ContaRepository.findById(creditarDTO.getContaId());
			if (conta != null) {
				conta.setSaldo(conta.getSaldo() + creditarDTO.getValorParaCreditar());
				ContaRepository.alterar(conta);
				return ResponseEntity.ok(conta);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		@PostMapping(value = "/transferir")
		public ResponseEntity<TransferenciaResponseDTO> transferirConta(@RequestBody TransferirDTO transferirDTO) {
			Conta contaOrigem = ContaRepository.findById(transferirDTO.getContaOrigemId());
			Conta contaDestino = ContaRepository.findById(transferirDTO.getContaDestinoId());
			if (contaOrigem != null && contaDestino != null) {
				contaOrigem.setSaldo(contaOrigem.getSaldo() - transferirDTO.getValorParaTransferir());
				contaDestino.setSaldo(contaDestino.getSaldo() + transferirDTO.getValorParaTransferir());
				ContaRepository.alterar(contaOrigem);
				ContaRepository.alterar(contaDestino);
				TransferenciaResponseDTO responseDTO = new TransferenciaResponseDTO();
				responseDTO.setContaOrigem(contaOrigem);
				responseDTO.setContaDestino(contaDestino);
				return ResponseEntity.ok(responseDTO);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		@GetMapping(value = "/saldo/{id}")
		public ResponseEntity<Conta> saldoConta(@PathVariable Long id) {
			Conta conta = ContaRepository.findById(id);
			if (conta != null) {
				return ResponseEntity.ok(conta);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
}