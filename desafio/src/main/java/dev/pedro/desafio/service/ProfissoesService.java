package dev.pedro.desafio.service;

import dev.pedro.desafio.model.Profissoes;
import dev.pedro.desafio.repository.ProfissoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProfissoesService implements ProfissoesInterface {
    @Autowired
    private ProfissoesRepository profissaoRepository;

    @Override
    public List<Profissoes> getAllProfissoes() {
        return StreamSupport.stream(profissaoRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}

