package pl.dariuszmedrala.organiser.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class WeatherDto {
    @JsonProperty("main")
    private TempDto tempDto;

    @JsonProperty("clouds")
    private CloudsDto cloudsDto;

    @JsonProperty("id")
    private int id;

    public static class TempDto {
        @JsonProperty("temp")
        private double temperature;
        public double getTemperature() {
            return temperature;
        }
    }

    public static class CloudsDto{
        @JsonProperty("all")
        private double clouds;
        public double getClouds(){
            return clouds;
        }
    }
}
