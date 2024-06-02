package dev.pedro.desafio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String contacto;
    private int idade;
    private String morada;

    @ManyToOne
    private Profissoes profissao;

    public Candidato() {
    }

    public Candidato(String nome, String contacto, int idade, String morada, Profissoes profissao) {
        this.nome = nome;
        this.contacto = contacto;
        this.idade = idade;
        this.morada = morada;
        this.profissao = profissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public Profissoes getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissoes profissao) {
        this.profissao = profissao;
    }

    @Override
    public String toString() {
        return "StoreCandidateInformation{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", contacto='" + contacto + '\'' +
                ", idade=" + idade +
                ", address='" + morada + '\'' +
                ", profissao=" + profissao.getDescricao() +
                '}';
    }
}
