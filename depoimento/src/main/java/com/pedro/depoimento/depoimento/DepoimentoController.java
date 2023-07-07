package com.pedro.depoimento.depoimento;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/depoimentos")
public class DepoimentoController {

    @Autowired
    private DepoimentoService depoimentoService;
    
    @GetMapping
    public ResponseEntity<HashSet<Depoimento>> getDepoimentos() {
        var depoimentos = depoimentoService.getDepoimentos();

        return ResponseEntity.ok().body(depoimentos);
    }


    @PostMapping
    public ResponseEntity<Depoimento> saveDepoimento(
        @RequestBody DepoimentoRequest depoimentoRequest
    ) {
        var depoimento = depoimentoService.saveDepoimento(depoimentoRequest);

        return ResponseEntity.ok().body(depoimento);
    }
}
