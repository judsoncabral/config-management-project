package br.ufrn.imd.banco.helper;

public class ObjetoInexistenteException extends Exception
{
	public ObjetoInexistenteException() {
        super();
	}

	public ObjetoInexistenteException(String message) {
        super(message);
	}
}
