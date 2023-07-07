package com.pedro.depoimento.depoimento;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DepoimentoService {
    
    @Autowired
    private DepoimentoRepository depoimentoRepository;
    
    public Page<Depoimento> getDepoimentos(Pageable pageable) {
        var depoimentos = depoimentoRepository.findAll(pageable);

        return depoimentos;
    }
    public HashSet<Depoimento> getHomeDepoimentos() {
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

    public Depoimento updateDepoimento(String depoimentoId, DepoimentoRequest depoimentoRequest) {

        var depoimentoToUpdate = depoimentoRepository.findById(depoimentoId).get();

        depoimentoToUpdate.setDepoimento(depoimentoRequest.getDepoimento());
        depoimentoToUpdate.setNome(depoimentoRequest.getNome());
        depoimentoToUpdate.setFoto(depoimentoRequest.getFoto());

        return depoimentoRepository.save(depoimentoToUpdate);
    }

    public void deleteDepoimento(String depoimentoId) {
        var depoimentoToDelete = depoimentoRepository.findById(depoimentoId).get();

        depoimentoToDelete.setDeleted(true);

        depoimentoRepository.save(depoimentoToDelete);
    }

}
