package dev.pedro.desafio.service;

import dev.pedro.desafio.model.Candidato;
import dev.pedro.desafio.repository.CandidatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CandidatoService implements CandidatoInterface {

    @Autowired
    private CandidatosRepository candidatosRepository;

    @Override
    public List<Candidato> getAllCandidatos() {
        try {
            return StreamSupport.stream(candidatosRepository.findAll().spliterator(), false).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar encontrar candidatos", e);
        }
    }

    @Override
    public Optional<Candidato> getCandidatoById(Long id) {
        try {
            return candidatosRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar encontrar candidato", e);
        }
    }

    @Override
    public Candidato createCandidato(Candidato candidato) {
        validateCandidato(candidato);
        try {
            return candidatosRepository.save(candidato);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar criar candidato", e);
        }
    }

    @Override
    public Candidato updateCandidato(Long id, Candidato candidato) {
        validateCandidato(candidato);
        try {
            Optional<Candidato> checkCandidate = candidatosRepository.findById(id);
            if (checkCandidate.isEmpty()) {
                throw new RuntimeException("Candidato não encontrado");
            }

            //Verificar que partes do candidato foram atualizadas para atualizar apenas essas partes
            Candidato candidateToUpdate = checkCandidate.get();

            //Se o nome do candidato foi atualizado, diferente de null e diferente do nome atual
            if (candidato.getNome() != null && !candidato.getNome().equals(candidateToUpdate.getNome())) {
                candidateToUpdate.setNome(candidato.getNome());
            }
            //Se a idade do candidato foi atualizada, diferente de 0 e diferente da idade atual
            if (candidato.getIdade() != 0 && candidato.getIdade() != candidateToUpdate.getIdade()) {
                candidateToUpdate.setIdade(candidato.getIdade());
            }
            //Se o contacto do candidato foi atualizado, diferente de null e diferente do contacto atual
            if (candidato.getContacto() != null && !candidato.getContacto().equals(candidateToUpdate.getContacto())) {
                candidateToUpdate.setContacto(candidato.getContacto());
            }
            //Se a morada do candidato foi atualizada, diferente de null e diferente da morada atual
            if (candidato.getMorada() != null && !candidato.getMorada().equals(candidateToUpdate.getMorada())) {
                candidateToUpdate.setMorada(candidato.getMorada());
            }
            //Se a profissão do candidato foi atualizada, diferente de null e diferente da profissão atual
            if (candidato.getProfissao() != null && !candidato.getProfissao().equals(candidateToUpdate.getProfissao())) {
                candidateToUpdate.setProfissao(candidato.getProfissao());
            }

            return candidatosRepository.save(candidateToUpdate);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar atualizar candidato", e);
        }
    }

    @Override
    public void deleteCandidato(Long id) {
        try {
            candidatosRepository.deleteById(id);
        }catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar eliminar candidato", e);
        }
    }

    private void validateCandidato(Candidato candidato) {
        if (candidato.getNome() == null || candidato.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do candidato não pode ser null/empty");
        }
        if (candidato.getIdade() <= 0 || candidato.getIdade() > 150) {
            throw new IllegalArgumentException("Idade do candidato deve ser entre 0 e 150 anos");
        }
        if (candidato.getContacto() == null || candidato.getContacto().trim().isEmpty() ||
                candidato.getContacto().length() != 9 || !candidato.getContacto().matches("[0-9]+")) {
            throw new IllegalArgumentException("Contacto do candidato deve ser numérico e ter 9 dígitos");
        }
        if (candidato.getMorada() == null || candidato.getMorada().trim().isEmpty()) {
            throw new IllegalArgumentException("Morada do candidato não pode ser null/empty");
        }
        if (candidato.getProfissao() == null || candidato.getProfissao().getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Profissão do candidato não pode ser null");
        }
    }
}
