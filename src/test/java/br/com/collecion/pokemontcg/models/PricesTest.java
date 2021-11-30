package br.com.collecion.pokemontcg.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PricesTest implements Models {
    private static final Normal normal = null;
    private static final ReverseHolofoil reverseHolofoil = null;
    private static final double averageSellPrice = 1.0;
    private static final double lowPrice = 10.0;
    private static final double trendPrice = 100.0;
    private static final Object germanProLow = new Object[]{ "germanProLow"};
    private static final Object suggestedPrice = new Object[]{ "suggestedPrice"};
    private static final Object reverseHoloSell = new Object[]{ "reverseHoloSell"};
    private static final Object reverseHoloLow = new Object[]{ "reverseHoloLow"};
    private static final Object reverseHoloTrend = new Object[]{ "reverseHoloTrend"};
    private static final double lowPriceExPlus = 1000.0;
    private static final double avg1 = 10000.0;
    private static final double avg7 = 100000.0;
    private static final double avg30 = 100000.0;
    private static final Object reverseHoloAvg1 = new Object[]{ "reverseHoloAvg1"};
    private static final Object reverseHoloAvg7 = new Object[]{ "reverseHoloAvg7"};
    private static final Object reverseHoloAvg30 = new Object[]{ "reverseHoloAvg30"};

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        Prices prices = new Prices(
                normal, reverseHolofoil, averageSellPrice, lowPrice, trendPrice,
                germanProLow, suggestedPrice, reverseHoloSell, reverseHoloLow, reverseHoloTrend,
                lowPriceExPlus, avg1, avg7, avg30, reverseHoloAvg1, reverseHoloAvg7, reverseHoloAvg30
        );
        check(prices);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        Prices prices = new Prices();
        prices.setNormal(normal);
        prices.setReverseHolofoil(reverseHolofoil);
        prices.setAverageSellPrice(averageSellPrice);
        prices.setLowPrice(lowPrice);
        prices.setTrendPrice(trendPrice);
        prices.setGermanProLow(germanProLow);
        prices.setSuggestedPrice(suggestedPrice);
        prices.setReverseHoloSell(reverseHoloSell);
        prices.setReverseHoloLow(reverseHoloLow);
        prices.setReverseHoloTrend(reverseHoloTrend);
        prices.setLowPriceExPlus(lowPriceExPlus);
        prices.setAvg1(avg1);
        prices.setAvg7(avg7);
        prices.setAvg30(avg30);
        prices.setReverseHoloAvg1(reverseHoloAvg1);
        prices.setReverseHoloAvg7(reverseHoloAvg7);
        prices.setReverseHoloAvg30(reverseHoloAvg30);
        check(prices);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Prices prices = Prices.builder()
                .normal(normal).reverseHolofoil(reverseHolofoil).averageSellPrice(averageSellPrice).lowPrice(lowPrice).trendPrice(trendPrice)
                .germanProLow(germanProLow).suggestedPrice(suggestedPrice).reverseHoloSell(reverseHoloSell).reverseHoloLow(reverseHoloLow)
                .reverseHoloTrend(reverseHoloTrend).lowPriceExPlus(lowPriceExPlus).avg1(avg1).avg7(avg7).avg30(avg30).reverseHoloAvg1(reverseHoloAvg1)
                .reverseHoloAvg7(reverseHoloAvg7).reverseHoloAvg30(reverseHoloAvg30)
                .build();
        check(prices);
    }

    private void check(Prices item){
        assertNotNull(item, createMsg("Prices"));
        assertEquals(normal, item.getNormal(), createMsg("normal"));
        assertEquals(reverseHolofoil, item.getReverseHolofoil(), createMsg("reverseHolofoil"));
        assertEquals(averageSellPrice, item.getAverageSellPrice(), createMsg("averageSellPrice"));
        assertEquals(lowPrice, item.getLowPrice(), createMsg("lowPrice"));
        assertEquals(trendPrice, item.getTrendPrice(), createMsg("trendPrice"));

        assertNotNull(item.getGermanProLow());
        assertEquals(germanProLow, item.getGermanProLow(), createMsg("germanProLow"));

        assertNotNull(item.getSuggestedPrice());
        assertEquals(suggestedPrice, item.getSuggestedPrice(), createMsg("suggestedPrice"));

        assertNotNull(item.getReverseHoloSell());
        assertEquals(reverseHoloSell, item.getReverseHoloSell(), createMsg("reverseHoloSell"));

        assertNotNull(item.getReverseHoloLow());
        assertEquals(reverseHoloLow, item.getReverseHoloLow(), createMsg("reverseHoloLow"));

        assertNotNull(item.getReverseHoloTrend());
        assertEquals(reverseHoloTrend, item.getReverseHoloTrend(), createMsg("reverseHoloTrend"));

        assertNotNull(item.getReverseHoloAvg7());
        assertEquals(reverseHoloAvg7, item.getReverseHoloAvg7(), createMsg("reverseHoloAvg7"));

        assertEquals(lowPriceExPlus, item.getLowPriceExPlus(), createMsg("lowPriceExPlus"));
        assertEquals(avg1, item.getAvg1(), createMsg("avg1"));
        assertEquals(avg7, item.getAvg7(), createMsg("avg7"));
        assertEquals(avg30, item.getAvg30(), createMsg("avg30"));


        assertNotNull(item.getReverseHoloAvg1());
        assertEquals(reverseHoloAvg1, item.getReverseHoloAvg1(), createMsg("reverseHoloAvg1"));

        assertNotNull(item.getReverseHoloAvg7());
        assertEquals(reverseHoloAvg7, item.getReverseHoloAvg7(), createMsg("reverseHoloAvg7"));

        assertNotNull(item.getReverseHoloAvg30());
        assertEquals(reverseHoloAvg30, item.getReverseHoloAvg30(), createMsg("reverseHoloAvg30"));
    }
}
