package com.flogramming.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.flogramming.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LazadaOrderTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LazadaOrder.class);
        LazadaOrder lazadaOrder1 = new LazadaOrder();
        lazadaOrder1.setId(1L);
        LazadaOrder lazadaOrder2 = new LazadaOrder();
        lazadaOrder2.setId(lazadaOrder1.getId());
        assertThat(lazadaOrder1).isEqualTo(lazadaOrder2);
        lazadaOrder2.setId(2L);
        assertThat(lazadaOrder1).isNotEqualTo(lazadaOrder2);
        lazadaOrder1.setId(null);
        assertThat(lazadaOrder1).isNotEqualTo(lazadaOrder2);
    }
}
