package com.denzhn.galacticgold;

import com.denzhn.galacticgold.model.CryptoCurrency;
import com.denzhn.galacticgold.repository.CryptoCurrencyRepository;
import com.denzhn.galacticgold.service.impl.CryptoCurrencyServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CryptoCurrencyServiceUnitTests {
    @Spy
    CryptoCurrencyRepository repository;
    @InjectMocks
    CryptoCurrencyServiceImpl service;

    @Test
    void should_ReturnEmptyList_ifRepositoryDataIsNotExists() {
        Assertions.assertNotNull(repository);
        Assertions.assertNotNull(service);
        Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertTrue(repository.findAll().isEmpty());
        Assertions.assertTrue(service.getAll().isEmpty());
    }

    @Test
    void should_ReturnDtoList_ifRepositoryDataIsExists() {
        Assertions.assertNotNull(repository);
        Assertions.assertNotNull(service);
        Mockito.when(repository.findAll()).thenReturn(List.of(new CryptoCurrency()));
        Assertions.assertFalse(repository.findAll().isEmpty());
        Assertions.assertFalse(service.getAll().isEmpty());
    }

    @Test
    void should_SaveAll_ifNoDataExists(){
        Assertions.assertNotNull(repository);
        Assertions.assertNotNull(service);
        //Mockito.when(repository.saveAll())
    }
}
