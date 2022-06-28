package com.denzhn.galacticgold.client.coincap;

import com.denzhn.galacticgold.client.ApiClient;
import com.denzhn.galacticgold.client.coincap.constants.CoinCapApiConstants;
import com.denzhn.galacticgold.dto.coincap.AssetsDataDto;
import com.denzhn.galacticgold.dto.coincap.AssetsDto;
import com.denzhn.galacticgold.model.CryptoCurrency;
import com.denzhn.galacticgold.populator.BasePopulator;
import com.denzhn.galacticgold.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public final class CoinCapClient implements ApiClient {
    private final WebClient webClient;
    private final CryptoCurrencyService cryptoCurrencyService;
    private final BasePopulator<AssetsDataDto, CryptoCurrency> cryptoCurrencyPopulator;

    @Override
    @Scheduled(fixedRate = 10000)
    public void updateAssets() {
        Mono<AssetsDto> assetsDtoMono = webClient.get().uri(CoinCapApiConstants.ASSETS).retrieve().bodyToMono(AssetsDto.class);
        assetsDtoMono.subscribe(assetsDto -> cryptoCurrencyService.update(assetsDto.getData().stream().map(cryptoCurrencyPopulator::populate).toList()));
        log.info("Api data Updated.");
    }
}
