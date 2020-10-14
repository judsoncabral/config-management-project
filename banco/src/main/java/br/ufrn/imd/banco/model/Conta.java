package br.ufrn.imd.banco.model;
import java.util.Random;

public class Conta {

	private long id;
	
	private Agencia agencia;

	private Double saldo;
	
	private Random gerador = new Random();
	
	public Conta() {
		super();
		this.id = gerador.nextLong();
		this.agencia = new Agencia();
		this.saldo = 0.0;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public Agencia getAgencia() {
		return agencia;
	}


	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}


	public Double getSaldo() {
		return saldo;
	}


	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
}