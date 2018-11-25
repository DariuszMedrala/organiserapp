package pl.dariuszmedrala.organiser.models.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    @Pattern(regexp = "[a-zA-Z0-9]{1,25}")
    private String login;
    @Pattern(regexp = "[a-zA-Z0-9!@#$%^&*().,:]{1,60}")
    private String password;
}
