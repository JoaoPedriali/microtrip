package com.pedro.depoimento.depoimento;

import java.util.HashSet;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DepoimentoRepository extends MongoRepository<Depoimento, String> {

    @Aggregation("{$sample: { size: 3}}")
    HashSet<Depoimento> sampleThreeRandomDepoimentos();
}
