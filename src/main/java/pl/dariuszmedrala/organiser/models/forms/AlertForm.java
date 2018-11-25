package pl.dariuszmedrala.organiser.models.forms;

import lombok.Data;


import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class AlertForm {
    @Pattern(regexp = "[0-9]{9}")
    private String phoneNumber;
    @Pattern(regexp = "[a-zA-Z0-9.,:!@#$%^&*()?]{1,160}")
    private String message;
    @Pattern(regexp = "[0-9]{4}-[0-1][0-9]-[0-3][0-9]")
    private String date;
    @Pattern(regexp = "[0-9]{2}:[0-9]{2}")
    private String hour;
}
