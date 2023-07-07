package com.pedro.depoimento.depoimento;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepoimentoService {
    
    @Autowired
    private DepoimentoRepository depoimentoRepository;
    
    public HashSet<Depoimento> getDepoimentos() {
        var depoimentos = depoimentoRepository.sampleThreeRandomDepoimentos();
        
        return depoimentos;
    }

    public Depoimento saveDepoimento(DepoimentoRequest depoimentoRequest) {

        var depoimentoToSave = new Depoimento(
            null, 
            depoimentoRequest.getNome(), 
            depoimentoRequest.getDepoimento(), 
            depoimentoRequest.getFoto(),
            false
        );

        return depoimentoRepository.save(depoimentoToSave);
    }


}
