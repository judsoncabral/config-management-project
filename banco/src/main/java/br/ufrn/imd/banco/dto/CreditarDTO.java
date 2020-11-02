package br.ufrn.imd.banco.dto;

public class CreditarDTO {
	private Long contaId;
	private double valorParaCreditar;
	
	public double getValorParaCreditar() {
		return valorParaCreditar;
	}
	public void setValorParaCreditar(double valorParaCreditar) {
		this.valorParaCreditar = valorParaCreditar;
	}
	public Long getContaId() {
		return contaId;
	}
	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}
}
