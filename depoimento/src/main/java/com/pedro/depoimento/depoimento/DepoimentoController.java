package com.pedro.depoimento.depoimento;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@EnableDiscoveryClient
public class DepoimentoController {

    @Autowired
    private DepoimentoService depoimentoService;
    
    @GetMapping("/depoimentos")
    public ResponseEntity<Page<Depoimento>> getDepoimentos(Pageable pageable) {
        var depoimentos = depoimentoService.getDepoimentos(pageable);
        
        return ResponseEntity.ok().body(depoimentos);
    }

    @GetMapping("/depoimentos-home")
    public ResponseEntity<HashSet<Depoimento>> getDepoimentosHome() {
        var depoimentos = depoimentoService.getHomeDepoimentos();

        return ResponseEntity.ok().body(depoimentos);
    }


    @PostMapping("/depoimentos")
    public ResponseEntity<Depoimento> saveDepoimento(
        @RequestBody DepoimentoRequest depoimentoRequest
    ) {
        var depoimento = depoimentoService.saveDepoimento(depoimentoRequest);

        return ResponseEntity.ok().body(depoimento);
    }

    @PutMapping("/depoimentos/{id}")
    public ResponseEntity<Depoimento> updateDepoimento(@PathVariable("id") String id, @RequestBody DepoimentoRequest depoimentoRequest) {
        var depoimento = depoimentoService.updateDepoimento(id, depoimentoRequest);

        return ResponseEntity.ok().body(depoimento);
    }

    @DeleteMapping("/depoimentos/{id}")
    public ResponseEntity<Object> deleteDepoimento(@PathVariable("id") String id) {
        depoimentoService.deleteDepoimento(id);
        return ResponseEntity.ok().build();
    }
}
