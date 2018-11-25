package pl.dariuszmedrala.organiser.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrencyDto {

    @JsonProperty("rates")
    private RatesDto[] ratesDto;


        public static class RatesDto {
            private String currency;
            @JsonProperty("mid")
            private double value;

            public String getCurrency() {
                return currency;
            }
            public double getValue() { return value; }
        }
    }

