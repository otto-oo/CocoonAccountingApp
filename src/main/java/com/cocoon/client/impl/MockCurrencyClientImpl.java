package com.cocoon.client.impl;

import com.cocoon.client.CurrencyClient;
import com.cocoon.dto.currency.CurrencyDto;
import com.cocoon.dto.currency.Rate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@ConditionalOnProperty(value = "mock.currency.enabled", havingValue = "true")
public class MockCurrencyClientImpl implements CurrencyClient {

    @Override
    public CurrencyDto retrieveCurrencyDetails() {
        CurrencyDto respo = new CurrencyDto();
        respo.rates = new Rate();
        respo.rates.GBP = 0.82d;
        respo.rates.JPY = 127.7d;
        respo.rates.RUB = 118.02d;
        respo.rates.USD = 1.10d;
        return respo;
    }
}
