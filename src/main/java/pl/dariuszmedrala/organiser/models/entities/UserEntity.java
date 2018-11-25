package pl.dariuszmedrala.organiser.models.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dariuszmedrala.organiser.models.forms.RegisterForm;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
public class UserEntity {
    private String login;
    String password;
    String city;
    String postalCode;
    @GeneratedValue
    @Id
    private int id;


    public UserEntity(RegisterForm registerForm){
        this.login = registerForm.getLogin();
        this.password = registerForm.getPassword();
        this.city = registerForm.getCity();
       this.postalCode = registerForm.getPostalCode();
    }
}
