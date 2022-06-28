package com.denzhn.galacticgold.dto.coincap;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CryptoReturnDto {
    private String id;
    private String rank;
    private String symbol;
    private String name;
    private BigDecimal price;
}
