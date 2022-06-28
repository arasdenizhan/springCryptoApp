package com.denzhn.galacticgold.controller;

import com.denzhn.galacticgold.dto.coincap.CryptoReturnDto;
import com.denzhn.galacticgold.model.CryptoCurrency;
import com.denzhn.galacticgold.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crypto")
@RequiredArgsConstructor
@CrossOrigin
public class CryptoCurrencyController {
    private final CryptoCurrencyService service;

    @GetMapping("/list")
    public ResponseEntity<List<CryptoReturnDto>> list() {
        List<CryptoReturnDto> cryptoCurrencies = service.getAll();
       return CollectionUtils.isEmpty(cryptoCurrencies) ? ResponseEntity.notFound().build() : ResponseEntity.ok(cryptoCurrencies);
    }
}
