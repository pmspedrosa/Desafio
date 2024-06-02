package dev.pedro.desafio;

import dev.pedro.desafio.model.Profissoes;
import dev.pedro.desafio.repository.ProfissoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Rest Controller para profissoes
*/
@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {
	@Autowired
	ProfissoesRepository profissaoRepository;

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Asegura que existem profissoes na base de dados
		if (profissaoRepository.count() == 0) {
			profissaoRepository.save(new Profissoes("Estudante"));
			profissaoRepository.save(new Profissoes("Desempregado"));
			profissaoRepository.save(new Profissoes("Trabalhador por conta de outrem"));
			profissaoRepository.save(new Profissoes("Trabalhador por conta própria"));
			profissaoRepository.save(new Profissoes("Outro"));
		}
	}
}
