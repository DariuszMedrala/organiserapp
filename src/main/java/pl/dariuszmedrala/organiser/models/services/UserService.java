package pl.dariuszmedrala.organiser.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dariuszmedrala.organiser.models.repositories.UserRepository;
import pl.dariuszmedrala.organiser.models.sessions.UserSession;
import pl.dariuszmedrala.organiser.models.entities.UserEntity;
import pl.dariuszmedrala.organiser.models.forms.RegisterForm;
import pl.dariuszmedrala.organiser.models.forms.UserForm;

import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;
    final PasswordHashingService passwordHashingService;
    final UserSession userSession;

    @Autowired
    public UserService(UserRepository userRepository, PasswordHashingService passwordHashingService, UserSession userSession){
        this.userRepository = userRepository;
        this.passwordHashingService = passwordHashingService;
        this.userSession = userSession;
    }


    public boolean isSuchLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public boolean tryLogin(UserForm userForm) {
        Optional<UserEntity> loginUser = userRepository.getUserByLogin(userForm.getLogin());

        if(!loginUser.isPresent()){
            return false;
        }

        if(passwordHashingService.match(loginUser.get().getPassword(), userForm.getPassword())){
            userSession.setLoggedIn(true);
            userSession.setUserEntity(loginUser.get());

            return true;
        }

        return false;
    }

    public void addUser(RegisterForm registerForm) {
        registerForm.setPassword(passwordHashingService.hash(registerForm.getPassword()));
        UserEntity newUser = new UserEntity(registerForm);
        userRepository.save(newUser);
    }

    public boolean isRepeatedPasswordCorrect(RegisterForm registerForm) {
        return registerForm.getPassword().equals(registerForm.getPasswordRepeat());
    }
}
