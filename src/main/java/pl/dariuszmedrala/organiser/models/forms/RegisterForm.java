package pl.dariuszmedrala.organiser.models.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class RegisterForm {
    @Pattern(regexp = "[a-zA-Z0-9]{1,25}")
    private String login;
    @Pattern(regexp = "[a-zA-Z0-9!@#$%^&*().,:]{1,60}")
    private String password;
    private String passwordRepeat;
    @Pattern(regexp = "[a-zA-Z//-]{1,25}")
    private String city;
    @Pattern(regexp = "[0-9]{5}")
    private String postalCode;
}
