package dev.pedro.desafio.repository;

import dev.pedro.desafio.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatosRepository extends CrudRepository<Candidato, Long>, JpaRepository<Candidato, Long> {
    // Só são utilizados os metodos extendidos do CrudRepositor,
    // no entanto, é possivel adicionar metodos personalizados como os exemplos abaixo

    List<Candidato> findByNome(String nome);
    List<Candidato> findByIdade(int idade);
    List<Candidato> findByMorada(String morada);

    @Modifying
    @Query("select i from Candidato i where i.profissao.id = :profissao_id")
    public List<Candidato> findByProfissaoId(@Param("profissao_id") String profissao_id);
}
