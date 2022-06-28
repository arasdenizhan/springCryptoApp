package com.denzhn.galacticgold.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class CryptoCurrency {
    @Id @Getter
    @GeneratedValue
    private Long id;

    @Getter @Setter
    @Column(nullable = false)
    private Integer rank;

    @Getter @Setter
    @Column(nullable = false)
    private String symbol;

    @Getter @Setter
    @Column(nullable = false)
    private String name;

    @Getter @Setter
    private BigDecimal supply;

    @Getter @Setter
    private BigDecimal maxSupply;

    @Getter @Setter
    private BigDecimal marketCapUsd;

    @Getter @Setter
    private BigDecimal volumeUsd24Hr;

    @Getter @Setter
    @Column(nullable = false)
    private BigDecimal priceUsd;

    @Getter @Setter
    private BigDecimal changePercent24Hr;

    @Getter @Setter
    private BigDecimal vwap24Hr;
}
