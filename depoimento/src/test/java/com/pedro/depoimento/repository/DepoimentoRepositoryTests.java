package com.pedro.depoimento.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.Random;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import com.pedro.depoimento.depoimento.Depoimento;
import com.pedro.depoimento.depoimento.DepoimentoRepository;

@DataMongoTest
@ActiveProfiles(profiles = {"test"})
public class DepoimentoRepositoryTests {

    @Autowired
    private DepoimentoRepository depoimentoRepository;

    @Test
    @DisplayName("Random sample should always return three items from the database")
    void randomSampleShouldReturnThreeElements() {
        var depoimentos = depoimentoRepository.sampleThreeRandomDepoimentos();

        assertEquals(3, depoimentos.size());

    }

    @Test
    @DisplayName("FindAll should omit deleted items")
    void findAllShouldNotIncludeDeletedItems() {

        Pageable pageable = PageRequest.of(0, 20);
        var depoimentos = depoimentoRepository.findByDeletedFalse(pageable);

        for(var dep : depoimentos.getContent()) {
            assertEquals(false, dep.isDeleted());
        }
    }

    @Test
    @DisplayName("Random sample should not return items marked as deleted")
    void randomSampleShouldNotReturnDeletedItems() {
        var depoimentos = depoimentoRepository.sampleThreeRandomDepoimentos();

        for (Depoimento dep : depoimentos) {
            assertEquals(false, dep.isDeleted());
        }
    }

    @Test
    @DisplayName("Random sample should be randomly ordered")
    void randomSampleShouldBeRandomlyOrdered() {
        var depoimentos1 = depoimentoRepository.sampleThreeRandomDepoimentos();
        var depoimentos2 = depoimentoRepository.sampleThreeRandomDepoimentos();

        assertNotEquals(depoimentos1, depoimentos2);
    }

    @BeforeEach
    private void preencherDepoimentos() {

        var depoimentos = new ArrayList<Depoimento>();

        for (int i = 0; i < 10; i++) {
            depoimentos.add(new Depoimento(null, Random.RANDOM_SEED_PROPERTY_NAME, Random.RANDOM_SEED_PROPERTY_NAME, "",
                    i % 2 == 0));
        }

        depoimentoRepository.saveAll(depoimentos);
    }
}
