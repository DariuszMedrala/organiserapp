package pl.dariuszmedrala.organiser.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.dariuszmedrala.organiser.models.dtos.CurrencyDto;
import pl.dariuszmedrala.organiser.models.sessions.UserSession;
import pl.dariuszmedrala.organiser.models.dtos.WeatherDto;
import pl.dariuszmedrala.organiser.models.forms.NoteForm;
import pl.dariuszmedrala.organiser.models.services.NoteService;
import pl.dariuszmedrala.organiser.models.services.WeatherAndCurrencyService;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class IndexController {

    final UserSession userSession;
    final NoteService noteService;
    final WeatherAndCurrencyService weatherAndCurrencyService;


    @Autowired
    public IndexController(UserSession userSession, NoteService noteService,
                           WeatherAndCurrencyService weatherAndCurrencyService){
        this.userSession = userSession;
        this.noteService = noteService;
        this.weatherAndCurrencyService = weatherAndCurrencyService;

    }

    @GetMapping("/")
    public String showMainScreen(Model model){
        WeatherDto weather = weatherAndCurrencyService.loadWeatherForCity(userSession.getUserEntity().getCity());
        CurrencyDto currencyDolar = weatherAndCurrencyService.loadCurrency("USD");
        CurrencyDto currencyEuro= weatherAndCurrencyService.loadCurrency("EUR");
        CurrencyDto currencyPound = weatherAndCurrencyService.loadCurrency("GBP");

        model.addAttribute("login", userSession.getUserEntity().getLogin());
        model.addAttribute("notes", noteService.getListOfNotesForToday());
        model.addAttribute("weather", (int) weather.getTempDto().getTemperature() - 273);
        model.addAttribute("clouds", (int) weather.getCloudsDto().getClouds());
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("city", userSession.getUserEntity().getCity());
        model.addAttribute("USD", currencyDolar.getRatesDto()[0].getValue());
        model.addAttribute("EUR", currencyEuro.getRatesDto()[0].getValue());
        model.addAttribute("GBP", currencyPound.getRatesDto()[0].getValue());
        return "index";
    }

    @GetMapping("/logout")
    public String logOut(){
        userSession.setLoggedIn(false);
        return "redirect:/";
    }

    @GetMapping("/user/newnote")
    public String showNewNote(Model model){
        model.addAttribute("noteForm", new NoteForm());
        return "createNote";
    }

    @PostMapping("/user/newnote")
    public String getNewNote(Model model, @Valid @ModelAttribute NoteForm noteForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("noteInfo", "Some data is incorrect");
            return "createNote";
        }
        noteService.addNote(noteForm);
        return "redirect:/";
    }
}
