package br.ufrn.imd.banco.repository;
 	
import java.util.ArrayList;
import java.util.List;
import br.ufrn.imd.banco.model.Conta;


public class ContaRepository {
	public static void populate() {
		for (int i = 0; i < 5; i++) {
			ContaRepository.contas.add(new Conta());
		}
	}
		
	public static List<Conta> contas = new ArrayList<Conta>();
	
	public static void adicionar(Conta conta) {
		if(conta != null) {
			contas.add(conta);
		}
	}
	
	public static void remover(Conta conta) {
		if(conta.getSaldo() == 0) {
			contas.remove(conta);
		}
	}
	
	public static void alterar(Conta conta) {
		for( int i = 0 ; i < contas.size(); i++) {
			if(contas.get(i).getId() == conta.getId()){
				contas.set(i,conta);
			}
		}
	}
	
	public static Conta findById(Long id) {
		for (int index = 0; index < ContaRepository.contas.size(); index++) {
			Conta conta = ContaRepository.contas.get(index);
			if (conta.getId() == id) {
				return conta;
			}
		}
		return null;
	}
}