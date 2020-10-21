package br.ufrn.imd.banco.dto;

public class DebitarDTO {
	private Long contaId;
	private double valorParaDebitar;
	public double getValorParaDebitar() {
		return valorParaDebitar;
	}
	public void setValorParaDebitar(double valorParaDebitar) {
		this.valorParaDebitar = valorParaDebitar;
	}
	public Long getContaId() {
		return contaId;
	}
	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}
}
