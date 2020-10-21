package br.ufrn.imd.banco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.ufrn.imd.banco.repository.ContaRepository;

@SpringBootApplication
public class BancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoApplication.class, args);
		
		ContaRepository.populate();
	}

}
