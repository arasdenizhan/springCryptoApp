package com.denzhn.galacticgold.populator.impl.coincap;

import com.denzhn.galacticgold.dto.coincap.AssetsDataDto;
import com.denzhn.galacticgold.model.CryptoCurrency;
import com.denzhn.galacticgold.populator.BasePopulator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public final class CryptoCurrencyPopulator extends BasePopulator<AssetsDataDto, CryptoCurrency> {

    public CryptoCurrencyPopulator() {
        super(AssetsDataDto.class, CryptoCurrency.class);
    }

    @Override
    public CryptoCurrency populate(AssetsDataDto source) {
        final CryptoCurrency targetInstance = createTargetInstance();
        targetInstance.setName(source.getName());
        targetInstance.setChangePercent24Hr(convertToBigDecimal(source.getChangePercent24Hr()));
        targetInstance.setMarketCapUsd(convertToBigDecimal(source.getMarketCapUsd()));
        targetInstance.setMaxSupply(convertToBigDecimal(source.getMarketCapUsd()));
        targetInstance.setRank(Integer.valueOf(source.getRank()));
        targetInstance.setPriceUsd(convertToBigDecimal(source.getPriceUsd()));
        targetInstance.setSupply(convertToBigDecimal(source.getSupply()));
        targetInstance.setSymbol(source.getSymbol());
        targetInstance.setVolumeUsd24Hr(convertToBigDecimal(source.getVolumeUsd24Hr()));
        targetInstance.setVwap24Hr(convertToBigDecimal(source.getVwap24Hr()));
        return targetInstance;
    }

    @Override
    public AssetsDataDto revertPopulate(CryptoCurrency cryptoCurrency) {
        return null;
    }

    private BigDecimal convertToBigDecimal(String input){
        return StringUtils.isEmpty(input) ? new BigDecimal(0) : new BigDecimal(input);
    }
}
