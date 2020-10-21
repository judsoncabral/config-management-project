package br.ufrn.imd.banco.dto;

public class TransferirDTO {
	private Long contaOrigemId;
	private Long contaDestinoId;
	private double valorParaTransferir;
	public double getValorParaTransferir() {
		return valorParaTransferir;
	}
	public void setValorParaTransferir(double valorParaTransferir) {
		this.valorParaTransferir = valorParaTransferir;
	}
	public Long getContaOrigemId() {
		return contaOrigemId;
	}
	public void setContaOrigemId(Long contaOrigemId) {
		this.contaOrigemId = contaOrigemId;
	}
	public Long getContaDestinoId() {
		return contaDestinoId;
	}
	public void setContaDestinoId(Long contaDestinoId) {
		this.contaDestinoId = contaDestinoId;
	}
}
