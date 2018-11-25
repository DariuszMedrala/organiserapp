package pl.dariuszmedrala.organiser.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.dariuszmedrala.organiser.models.forms.RegisterForm;
import pl.dariuszmedrala.organiser.models.repositories.UserRepository;
import pl.dariuszmedrala.organiser.models.entities.UserEntity;

import java.util.Optional;

@Service
public class ApiService {

    final UserRepository userRepository;
    final PasswordHashingService passwordHashingService;

    @Autowired
    public ApiService(UserRepository userRepository, PasswordHashingService passwordHashingService) {
        this.userRepository = userRepository;
        this.passwordHashingService = passwordHashingService;
    }

    public ResponseEntity getUserById(int id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            return ResponseEntity.ok(userEntityOptional.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body("User not found");
    }


    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity deleteById(int id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("User not found");
    }

    public ResponseEntity addNewUser(RegisterForm registerForm) {
        registerForm.setPassword(passwordHashingService.hash(registerForm.getPassword()));
        if (!userRepository.existsByLogin(registerForm.getLogin())){
            userRepository.save(new UserEntity(registerForm));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Login already exists");
    }
}
