package com.hcl.dbclm.repository;


import com.hcl.dbclm.entity.Nace;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class NaceRepositoryTest {

    @Autowired
    private NaceRepository naceRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void getNaceDetails_returnNaceDetails(){
        Nace nace = Nace.builder().level("1").description("AGRICULTURE, FORESTRY AND FISHING").build();
        Nace savedNaceDetails = entityManager.persistAndFlush(nace);
        assertThat(savedNaceDetails).isNotNull();
        assertThat(savedNaceDetails.getLevel()).isEqualTo("1");
        assertThat(savedNaceDetails.getDescription()).isEqualTo("AGRICULTURE, FORESTRY AND FISHING");
        assertThat(savedNaceDetails.getNaceOrder()).isNotNull();
    }
}