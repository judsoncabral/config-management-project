package br.ufrn.imd.banco.dto;

import br.ufrn.imd.banco.model.Conta;

public class TransferenciaResponseDTO {
	private Conta contaOrigem;
	private Conta contaDestino;
	
	public Conta getContaOrigem() {
		return contaOrigem;
	}
	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}
	public Conta getContaDestino() {
		return contaDestino;
	}
	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}
}
