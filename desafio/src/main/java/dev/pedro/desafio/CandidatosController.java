package dev.pedro.desafio;

import dev.pedro.desafio.model.Candidato;
import dev.pedro.desafio.service.CandidatoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/*
REST Controller para Candidatos
*/
@RestController
@RequestMapping("/candidatos")
public class CandidatosController {

    @Autowired
    private CandidatoInterface candidatoInterface;

    @GetMapping("/test")
    public String teste() {
        return "Hello, World!";
    }

    //GET /candidatos - Retorna todos os candidatos
    @GetMapping("")
    public ResponseEntity<List<Candidato>> getCandidatos() {
        return ResponseEntity.ok(candidatoInterface.getAllCandidatos());
    }

    //POST /candidatos - Cria um ou mais candidatos
    @PostMapping("")
    public ResponseEntity<?> createCandidatos(@RequestBody List<Candidato> candidates) {
        try {
            List<Candidato> savedCandidates = candidates.stream()
                    .map(candidatoInterface::createCandidato)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCandidates);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao tentar criar candidatos");
        }
    }

    //GET /candidatos/{id} - Retorna um candidato específico
    @GetMapping("/{id}")
    public ResponseEntity<Candidato> getCandidato(@PathVariable Long id) {
        return candidatoInterface.getCandidatoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //PUT /candidatos/{id} - Atualiza um candidato específico
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCandidato(@PathVariable Long id, @RequestBody Candidato candidato) {
        try {
            return ResponseEntity.ok(candidatoInterface.updateCandidato(id, candidato));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao tentar atualizar candidato");
        }
    }

    //DELETE /candidatos/{id} - Elimina um candidato específico
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCandidato(@PathVariable Long id) {
        try {
            candidatoInterface.deleteCandidato(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao tentar eliminar candidato");
        }
    }

    //GET /candidatos/profissao/{profissao_id} - Retorna todos os candidatos com uma profissão específica
    @GetMapping("/profissao/{profissao_id}")
    public ResponseEntity<?> searchByProfissao(@PathVariable String profissao_id) {
        try {
            return ResponseEntity.ok(candidatoInterface.getCandidatoByProfissaoId(profissao_id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao tentar encontrar candidatos");
        }
    }

}

