package br.ufrn.imd.banco.repository;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.banco.helper.ObjetoExistenteException;
import br.ufrn.imd.banco.helper.ObjetoInexistenteException;
import br.ufrn.imd.banco.helper.ObjetoVazioException;
import br.ufrn.imd.banco.model.Agencia;


public class AgenciaRepository {
	
	public static ArrayList<Agencia> agencias = new ArrayList<Agencia>();
	
	public static void adicionar(Agencia agencia) throws ObjetoExistenteException {
		try {
			Agencia busca = AgenciaRepository.buscar(agencia.getId());
			if(busca != null)
			{
				throw new ObjetoExistenteException("Já há uma agência com essse id");
			}		
		} catch(ObjetoVazioException e) { //Lista de agências está vazia, então, insiro.
			if(agencia != null) {
				agencias.add(agencia);
			}
		} catch(ObjetoInexistenteException e) { //Não existe uma agência com esse id, então, insiro.
			if(agencia != null) {
				agencias.add(agencia);
			}
		}
	}
	
	public static Agencia buscar(long id) throws ObjetoVazioException, ObjetoInexistenteException {
		if(!agencias.isEmpty()) {
			int i;
			Agencia agencia;
			for(i = 0; i < agencias.size(); i++) {
				agencia = agencias.get(i);							
				if(agencia.getId() == id) {
					return agencia;
				}
			}
		} else {
			throw new ObjetoVazioException("Não há agências.");//Lista de agências vazia
		}
		throw new ObjetoInexistenteException("Não há agência com esse id.");//Nenhuma agência com esse id na lista
	}
}
