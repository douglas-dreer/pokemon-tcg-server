package br.com.collecion.pokemontcg.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SetTest implements Models {
    private static final String id = "id";
    private static final String name = "name";
    private static final String series = "series";
    private static final int printedTotal = 1;
    private static final int total = 10;
    private static final Legalities legalities = new Legalities();
    private static final String ptcgoCode = "ptcgoCode";
    private static final String releaseDate = "releaseDate";
    private static final String updatedAt = "updatedAt";
    private static final Images images = new Images();

    @BeforeEach
    public void setup() {
        legalities.setExpanded("expanded");
        legalities.setStandard("standart");
        legalities.setUnlimited("unlimited");

        images.setLarge("large");
        images.setSmall("small");
        images.setLogo("logo");
        images.setSymbol("symbol");
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        Set set = new Set(id, name, series, printedTotal, total, legalities, ptcgoCode, releaseDate, updatedAt, images);
        check(set);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        Set set = new Set();
        set.setId(id);
        set.setName(name);
        set.setSeries(series);
        set.setPrintedTotal(printedTotal);
        set.setTotal(total);
        set.setLegalities(legalities);
        set.setPtcgoCode(ptcgoCode);
        set.setReleaseDate(releaseDate);
        set.setUpdatedAt(updatedAt);
        set.setImages(images);
        check(set);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Set set = Set.builder()
                .id(id).name(name).series(series)
                .printedTotal(printedTotal).total(total)
                .legalities(legalities).ptcgoCode(ptcgoCode).releaseDate(releaseDate)
                .updatedAt(updatedAt).images(images)
                .build();
        check(set);
    }

    private void check(Set item){
        assertNotNull(item, createMsg("Set"));
        assertEquals(id, item.getId(), createMsg("id"));
        assertEquals(name, item.getName(), createMsg("name"));
        assertEquals(series, item.getSeries(), createMsg("series"));
        assertEquals(printedTotal, item.getPrintedTotal(), createMsg("printedTotal"));
        assertEquals(total, item.getTotal(), createMsg("total"));
        assertNotNull(legalities, createMsg("legalities"));
        assertEquals(legalities, item.getLegalities(), createMsg("legalities"));
        assertEquals(ptcgoCode, item.getPtcgoCode(), createMsg("ptcgoCode"));
        assertEquals(releaseDate, item.getReleaseDate(), createMsg("releaseDate"));
        assertNotNull(images, createMsg("images"));
        assertEquals(images, item.getImages(), createMsg("images"));
        assertEquals(updatedAt, item.getUpdatedAt(), createMsg("updatedAt"));
    }
}
