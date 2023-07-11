package com.pedro.depoimento.depoimento;

import java.util.HashSet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepoimentoRepository extends MongoRepository<Depoimento, String> {

    @Aggregation(pipeline = { "{$match: {deleted: false}}", "{$sample: { size: 3}}" })
    HashSet<Depoimento> sampleThreeRandomDepoimentos();

    Page<Depoimento> findByDeletedFalse(Pageable pageable);
}
