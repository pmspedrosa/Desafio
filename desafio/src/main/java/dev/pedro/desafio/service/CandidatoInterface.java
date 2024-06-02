package dev.pedro.desafio.service;

import dev.pedro.desafio.model.Candidato;
import java.util.List;
import java.util.Optional;

public interface CandidatoInterface {
    List<Candidato> getAllCandidatos();
    Optional<Candidato> getCandidatoById(Long id);
    Candidato createCandidato(Candidato candidato);
    Candidato updateCandidato(Long id, Candidato candidato);
    void deleteCandidato(Long id);
}
