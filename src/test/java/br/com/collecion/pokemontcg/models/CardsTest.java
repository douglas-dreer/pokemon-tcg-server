package br.com.collecion.pokemontcg.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CardsTest implements Models {
    private static final List<Card> data = Collections.singletonList(new Card());
    private static final int page = 1;
    private static final int pageSize = 50;
    private static final int count = 1;
    private static final int totalCount = 100;

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        Cards cards = new Cards(data, page, pageSize, count, totalCount);
        check(cards);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        Cards cards = new Cards();
        cards.setData(data);
        cards.setPage(page);
        cards.setPageSize(pageSize);
        cards.setCount(count);
        cards.setTotalCount(totalCount);
        check(cards);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Cards cards = Cards.builder()
                .data(data).page(page).pageSize(pageSize)
                .count(count).totalCount(totalCount)
                .build();
        check(cards);
    }

    private void check(Cards item){
        assertNotNull(item, createMsg("Cards"));
        assertFalse(item.getData().isEmpty(), "data");
        assertEquals(data, item.getData(), createMsg("data"));
        assertEquals(page, item.getPage(), createMsg("page"));
        assertEquals(pageSize, item.getPageSize(), createMsg("pageSize"));
        assertEquals(count, item.getCount(), createMsg("count"));
        assertEquals(totalCount, item.getTotalCount(), createMsg("totalCount"));
    }
}
