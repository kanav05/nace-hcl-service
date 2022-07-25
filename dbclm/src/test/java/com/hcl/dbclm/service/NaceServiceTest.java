package com.hcl.dbclm.service;

import com.hcl.dbclm.entity.Nace;
import com.hcl.dbclm.repository.NaceRepository;
import com.hcl.dbclm.serviceimpl.NaceServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
public class NaceServiceTest {

    @Mock
    private NaceRepository naceRepository;
    @Mock
    private NaceServiceImpl naceService;

   /* @BeforeEach
    public void setUp() {
        naceService = new NaceService(naceRepository);
    }*/

    @Test
    @Disabled
    void getNaceDetails_shouldReturnNaceDetails() {

        given(naceRepository.findByNaceCode("A")).willReturn(Nace.builder().level("1").description("AGRICULTURE, FORESTRY AND FISHING").build());
        Nace nace = naceService.findByNaceCode("A");
        assertThat(nace).isNotNull();
        assertThat(nace.getLevel()).isEqualTo("1");
        assertThat(nace.getDescription()).isEqualTo("AGRICULTURE, FORESTRY AND FISHING");

    }

}
