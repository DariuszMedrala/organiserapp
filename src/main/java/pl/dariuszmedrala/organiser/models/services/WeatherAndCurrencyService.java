package pl.dariuszmedrala.organiser.models.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.dariuszmedrala.organiser.models.dtos.CurrencyDto;
import pl.dariuszmedrala.organiser.models.dtos.WeatherDto;



@Service
public class WeatherAndCurrencyService {

    @Value("${api.key}")
    String API_KEY;

    public WeatherDto loadWeatherForCity(String city) {
        return getRestTemplate().getForObject("https://api.openweathermap.org/data/2.5/weather?q="
                + city
                +"&appid="
                + API_KEY, WeatherDto.class);
    }

    public CurrencyDto loadCurrency(String code) {
        return getRestTemplate().getForObject("http://api.nbp.pl/api/exchangerates/rates/a/"
                + code
                +"/?format=json",
                CurrencyDto.class);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
