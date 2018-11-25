package pl.dariuszmedrala.organiser.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dariuszmedrala.organiser.models.forms.RegisterForm;
import pl.dariuszmedrala.organiser.models.services.ApiService;

@RestController
public class ApiController {
    final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity getUserById(@PathVariable("id") int id) {
        return apiService.getUserById(id);
    }

    @GetMapping("/api/user")
    public ResponseEntity getAllUsers() {
        return apiService.getAllUsers();
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") int id) {
        return apiService.deleteById(id);
    }

    @PostMapping(value = "/api/user", consumes = "application/json")
    public ResponseEntity saveNewUser(@RequestBody RegisterForm registerForm) {
        return apiService.addNewUser(registerForm);
    }


}
