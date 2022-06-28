package com.denzhn.galacticgold.service;

import com.denzhn.galacticgold.dto.coincap.CryptoReturnDto;
import com.denzhn.galacticgold.model.CryptoCurrency;

import java.util.List;

public interface CryptoCurrencyService {
    void update(List<CryptoCurrency> cryptoCurrencies);
    List<CryptoReturnDto> getAll();
    void updateCryptoCurrencyByName(CryptoCurrency cryptoCurrency);
}
