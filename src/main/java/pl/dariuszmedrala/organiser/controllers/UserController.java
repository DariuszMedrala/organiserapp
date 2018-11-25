package pl.dariuszmedrala.organiser.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dariuszmedrala.organiser.models.sessions.UserSession;
import pl.dariuszmedrala.organiser.models.forms.RegisterForm;
import pl.dariuszmedrala.organiser.models.forms.UserForm;
import pl.dariuszmedrala.organiser.models.services.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    final UserService userService;
    final UserSession userSession;

    public UserController(UserService userService, UserSession userSession) {
        this.userSession = userSession;
        this.userService = userService;
    }

    @GetMapping("/user/login")
    public String showLoginForm(Model model){
        model.addAttribute("userForm", new UserForm());
        return "userLogin";
    }

    @PostMapping("/user/login")
    public String getLoginForm(Model model, @Valid @ModelAttribute UserForm userForm, BindingResult bindingResult){
        if (bindingResult.hasErrors() || !userService.tryLogin(userForm)) {
            model.addAttribute("loginInfo", "Login or password is incorrect");
            return "userLogin";
        }else {
            userSession.setLoggedIn(true);
            return "redirect:/";
        }
    }

    @GetMapping("/user/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "userRegistration";
    }

    @PostMapping("/user/register")
    public String getRegistrationForm(Model model, @ModelAttribute @Valid RegisterForm registerForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("registerInfo", "Some data is incorrect, try 44336 zip code format");
            return "userRegistration";
        }
        if(userService.isSuchLogin(registerForm.getLogin())){
            model.addAttribute("registerInfo", "This login is already taken");
            return "userRegistration";
        }
        if(!userService.isRepeatedPasswordCorrect(registerForm)){
            model.addAttribute("registerInfo", "Repeated password is incorrect");
            return "userRegistration";
        }
        userService.addUser(registerForm);
        return "redirect:/";
    }

}
