package com.denzhn.galacticgold;

import com.denzhn.galacticgold.controller.CryptoCurrencyController;
import com.denzhn.galacticgold.dto.coincap.CryptoReturnDto;
import com.denzhn.galacticgold.model.CryptoCurrency;
import com.denzhn.galacticgold.repository.CryptoCurrencyRepository;
import com.denzhn.galacticgold.service.CryptoCurrencyService;
import com.denzhn.galacticgold.service.impl.CryptoCurrencyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CryptoCurrencyControllerUnitTests {

    @Spy
    CryptoCurrencyService service;

    @InjectMocks
    CryptoCurrencyController controller;

    @Test
    void should_ReturnOk_ifServiceReturnDataIsNotEmpty() {
        Assertions.assertNotNull(service);
        Assertions.assertNotNull(controller);
        CryptoReturnDto testData = CryptoReturnDto.builder().id("1").name("test").rank("1").price(BigDecimal.ZERO).build();
        Mockito.when(service.getAll()).thenReturn(List.of(testData));
        Assertions.assertEquals(HttpStatus.OK, controller.list().getStatusCode());
    }

    @Test
    void should_ReturnNotFound_ifServiceReturnDataIsEmpty() {
        Assertions.assertNotNull(service);
        Assertions.assertNotNull(controller);
        Mockito.when(service.getAll()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, controller.list().getStatusCode());
    }
}
