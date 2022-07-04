package com.flogramming.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.flogramming.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ShopItemsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShopItems.class);
        ShopItems shopItems1 = new ShopItems();
        shopItems1.setId(1L);
        ShopItems shopItems2 = new ShopItems();
        shopItems2.setId(shopItems1.getId());
        assertThat(shopItems1).isEqualTo(shopItems2);
        shopItems2.setId(2L);
        assertThat(shopItems1).isNotEqualTo(shopItems2);
        shopItems1.setId(null);
        assertThat(shopItems1).isNotEqualTo(shopItems2);
    }
}
