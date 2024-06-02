package dev.pedro.desafio;

import dev.pedro.desafio.model.Candidato;
import dev.pedro.desafio.model.Profissoes;
import dev.pedro.desafio.repository.CandidatosRepository;
import dev.pedro.desafio.service.CandidatoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UnitTests {

        @Mock
        private CandidatosRepository candidatosRepository;

        @InjectMocks
        private CandidatoService candidatoService;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testGetAllCandidatos_Success() {
            when(candidatosRepository.findAll()).thenReturn(List.of(new Candidato(), new Candidato()));

            assertEquals(2, candidatoService.getAllCandidatos().size());
            verify(candidatosRepository, times(1)).findAll();
        }

        @Test
        public void testGetAllCandidatos_Exception() {
            when(candidatosRepository.findAll()).thenThrow(new RuntimeException("Ocorreu um erro ao tentar encontrar candidatos"));

            Exception exception = assertThrows(RuntimeException.class, () -> {
                candidatoService.getAllCandidatos();
            });

            String expectedMessage = "Ocorreu um erro ao tentar encontrar candidatos";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
            verify(candidatosRepository, times(1)).findAll();
        }

        @Test
        public void testGetCandidatoById_Success() {
            Candidato candidato = new Candidato();
            candidato.setNome("Teste");

            when(candidatosRepository.findById(1L)).thenReturn(Optional.of(candidato));

            Optional<Candidato> foundCandidato = candidatoService.getCandidatoById(1L);

            assertTrue(foundCandidato.isPresent());
            assertEquals("Teste", foundCandidato.get().getNome());
            verify(candidatosRepository, times(1)).findById(1L);
        }

        @Test
        public void testGetCandidatoById_NotFound() {
            when(candidatosRepository.findById(1L)).thenReturn(Optional.empty());

            Optional<Candidato> foundCandidato = candidatoService.getCandidatoById(1L);

            assertTrue(foundCandidato.isEmpty());
            verify(candidatosRepository, times(1)).findById(1L);
        }

        @Test
        public void testGetCandidatoById_Exception() {
            when(candidatosRepository.findById(1L)).thenThrow(new RuntimeException("Ocorreu um erro ao tentar encontrar candidato"));

            Exception exception = assertThrows(RuntimeException.class, () -> {
                candidatoService.getCandidatoById(1L);
            });

            String expectedMessage = "Ocorreu um erro ao tentar encontrar candidato";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
            verify(candidatosRepository, times(1)).findById(1L);
        }


        @Test
        public void testCreateCandidato_Success() {
            Candidato candidato = new Candidato();
            Profissoes profissao = new Profissoes();
            profissao.setId(1L);
            profissao.setDescricao("Trabalhador por conta de outrem");
            candidato.setNome("Teste");
            candidato.setIdade(25);
            candidato.setContacto("123456789");
            candidato.setMorada("Teste Morada");
            candidato.setProfissao(profissao);

            when(candidatosRepository.save(any(Candidato.class))).thenReturn(candidato);

            Candidato createdCandidato = candidatoService.createCandidato(candidato);

            assertEquals("Teste", createdCandidato.getNome());
            verify(candidatosRepository, times(1)).save(candidato);
        }

        @Test
        public void testCreateCandidato_InvalidNome() {
            Candidato candidato = new Candidato();
            candidato.setNome(""); // Nome inválido

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                candidatoService.createCandidato(candidato);
            });

            String expectedMessage = "Nome do candidato não pode ser null/empty";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        @Test
        public void testCreateCandidato_Exception() {
            Candidato candidato = new Candidato();
            Profissoes profissao = new Profissoes();
            profissao.setId(1L);
            profissao.setDescricao("Trabalhador por conta de outrem");
            candidato.setNome("Teste");
            candidato.setIdade(25);
            candidato.setContacto("123456789");
            candidato.setMorada("Teste Morada");
            candidato.setProfissao(profissao);

            when(candidatosRepository.save(any(Candidato.class))).thenThrow(new RuntimeException("Ocorreu um erro ao tentar criar candidato"));

            Exception exception = assertThrows(RuntimeException.class, () -> {
                candidatoService.createCandidato(candidato);
            });

            String expectedMessage = "Ocorreu um erro ao tentar criar candidato";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
            verify(candidatosRepository, times(1)).save(candidato);
        }



        @Test
        public void testUpdateCandidato_Success() {
            Candidato candidato = new Candidato();
            Profissoes profissao = new Profissoes();
            profissao.setId(1L);
            profissao.setDescricao("Trabalhador por conta de outrem");
            candidato.setNome("Teste");
            candidato.setIdade(25);
            candidato.setContacto("123456789");
            candidato.setMorada("Teste Morada");
            candidato.setProfissao(profissao);

            Candidato candidatoToUpdate = new Candidato();
            candidatoToUpdate.setNome("Teste Atualizado");
            Profissoes profissaoToUpdate = new Profissoes();
            profissaoToUpdate.setId(1L);
            profissaoToUpdate.setDescricao("Trabalhador por conta de outrem");
            candidatoToUpdate.setIdade(25);
            candidatoToUpdate.setContacto("123456789");
            candidatoToUpdate.setMorada("Teste Morada");
            candidatoToUpdate.setProfissao(profissao);


            when(candidatosRepository.findById(1L)).thenReturn(Optional.of(candidato));
            when(candidatosRepository.save(any(Candidato.class))).thenReturn(candidatoToUpdate);

            Candidato updatedCandidato = candidatoService.updateCandidato(1L, candidatoToUpdate);

            assertEquals("Teste Atualizado", updatedCandidato.getNome());
            verify(candidatosRepository, times(1)).findById(1L);
            verify(candidatosRepository, times(1)).save(candidato);
        }

        @Test
        public void testUpdateCandidato_Exception() {
            Candidato candidato = new Candidato();
            Profissoes profissao = new Profissoes();
            profissao.setId(1L);
            profissao.setDescricao("Trabalhador por conta de outrem");
            candidato.setNome("Teste");
            candidato.setIdade(25);
            candidato.setContacto("123456789");
            candidato.setMorada("Teste Morada");
            candidato.setProfissao(profissao);

            Candidato candidatoToUpdate = new Candidato();
            candidatoToUpdate.setNome("Teste Atualizado");
            Profissoes profissaoToUpdate = new Profissoes();
            profissaoToUpdate.setId(1L);
            profissaoToUpdate.setDescricao("Trabalhador por conta de outrem");
            candidatoToUpdate.setIdade(25);
            candidatoToUpdate.setContacto("123456789");
            candidatoToUpdate.setMorada("Teste Morada");
            candidatoToUpdate.setProfissao(profissao);

            when(candidatosRepository.findById(1L)).thenReturn(Optional.of(candidato));
            when(candidatosRepository.save(any(Candidato.class))).thenThrow(new RuntimeException("Ocorreu um erro ao tentar atualizar candidato"));

            Exception exception = assertThrows(RuntimeException.class, () -> {
                candidatoService.updateCandidato(1L, candidatoToUpdate);
            });

            String expectedMessage = "Ocorreu um erro ao tentar atualizar candidato";
            String actualMessage = exception.getMessage();
            System.out.println(actualMessage);

            assertTrue(actualMessage.contains(expectedMessage));
            verify(candidatosRepository, times(1)).findById(1L);
            verify(candidatosRepository, times(1)).save(candidato);
        }

        @Test
        public void testDeleteCandidato_Success() {
            doNothing().when(candidatosRepository).deleteById(1L);

            candidatoService.deleteCandidato(1L);

            verify(candidatosRepository, times(1)).deleteById(1L);
        }

        @Test
        public void testDeleteCandidato_Exception() {
            doThrow(new RuntimeException("Ocorreu um erro ao tentar eliminar candidato")).when(candidatosRepository).deleteById(1L);

            Exception exception = assertThrows(RuntimeException.class, () -> {
                candidatoService.deleteCandidato(1L);
            });

            String expectedMessage = "Ocorreu um erro ao tentar eliminar candidato";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
            verify(candidatosRepository, times(1)).deleteById(1L);
        }

}


