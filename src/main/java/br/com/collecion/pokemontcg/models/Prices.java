package br.com.collecion.pokemontcg.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Prices {
    public Normal normal;
    private ReverseHolofoil reverseHolofoil;
    private double averageSellPrice;
    private double lowPrice;
    private double trendPrice;
    private Object germanProLow;
    private Object suggestedPrice;
    private Object reverseHoloSell;
    private Object reverseHoloLow;
    private Object reverseHoloTrend;
    private double lowPriceExPlus;
    private double avg1;
    private double avg7;
    private double avg30;
    private Object reverseHoloAvg1;
    private Object reverseHoloAvg7;
    private Object reverseHoloAvg30;
}
