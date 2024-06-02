package dev.pedro.desafio;

import dev.pedro.desafio.model.Profissoes;
import dev.pedro.desafio.service.ProfissoesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profissoes")
public class ProfissoesController {
    @Autowired
    private ProfissoesInterface profissoesInterface;

    @GetMapping("")
    public ResponseEntity<List<Profissoes>> getProfissoes() {
        return ResponseEntity.ok(profissoesInterface.getAllProfissoes());
    }

}
