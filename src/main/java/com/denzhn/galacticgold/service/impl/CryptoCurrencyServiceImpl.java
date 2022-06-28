package com.denzhn.galacticgold.service.impl;

import com.denzhn.galacticgold.dto.coincap.CryptoReturnDto;
import com.denzhn.galacticgold.model.CryptoCurrency;
import com.denzhn.galacticgold.repository.CryptoCurrencyRepository;
import com.denzhn.galacticgold.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {
    private final CryptoCurrencyRepository repository;
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void update(List<CryptoCurrency> cryptoCurrencies) {
        if(repository.count()==0){
            repository.saveAll(cryptoCurrencies);
        } else {
            cryptoCurrencies.forEach(this::updateCryptoCurrencyByName);
        }
    }

    @Override
    public List<CryptoReturnDto> getAll() {
        return repository.findAll().stream().map(cryptoCurrency ->
                CryptoReturnDto.builder()
                .id(String.valueOf(cryptoCurrency.getId()))
                .rank(String.valueOf(cryptoCurrency.getRank()))
                .symbol(cryptoCurrency.getSymbol())
                .price(cryptoCurrency.getPriceUsd())
                .name(cryptoCurrency.getName()).build()).toList();
    }

    @Override
    public void updateCryptoCurrencyByName(CryptoCurrency cryptoCurrency) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<CryptoCurrency> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(CryptoCurrency.class);
            Root<CryptoCurrency> root = criteriaUpdate.from(CryptoCurrency.class);
            criteriaUpdate.set("rank", cryptoCurrency.getRank());
            criteriaUpdate.set("symbol", cryptoCurrency.getSymbol());
            criteriaUpdate.set("supply", cryptoCurrency.getRank());
            criteriaUpdate.set("maxSupply", cryptoCurrency.getMaxSupply());
            criteriaUpdate.set("marketCapUsd", cryptoCurrency.getMarketCapUsd());
            criteriaUpdate.set("volumeUsd24Hr", cryptoCurrency.getVolumeUsd24Hr());
            criteriaUpdate.set("priceUsd", cryptoCurrency.getPriceUsd());
            criteriaUpdate.set("changePercent24Hr", cryptoCurrency.getChangePercent24Hr());
            criteriaUpdate.set("vwap24Hr", cryptoCurrency.getVwap24Hr());
            criteriaUpdate.where(criteriaBuilder.equal(root.get("name"), cryptoCurrency.getName()));
            entityManager.createQuery(criteriaUpdate).executeUpdate();
        } catch (Exception e){
            log.error("Error on queryCryptoCurrencyByName -> {}", ExceptionUtils.getStackTrace(e));
        }
    }
}
