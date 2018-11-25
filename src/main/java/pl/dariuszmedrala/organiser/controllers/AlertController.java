package pl.dariuszmedrala.organiser.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.dariuszmedrala.organiser.models.forms.AlertForm;
import pl.dariuszmedrala.organiser.models.forms.UserForm;
import pl.dariuszmedrala.organiser.models.services.AlertService;
import pl.dariuszmedrala.organiser.models.sessions.UserSession;

import javax.validation.Valid;

@Controller
public class AlertController {

    final AlertService alertService;
    final UserSession userSession;
    @Autowired
    public AlertController(AlertService alertService, UserSession userSession) {
        this.alertService = alertService;
        this.userSession = userSession;
    }

    @GetMapping("/user/alerts")
    public String showAlertMenu(Model model){
        model.addAttribute("alerts", alertService.findAllByLogin(userSession.getUserEntity().getLogin()));
        return "alerts";
    }
    @GetMapping("/user/alert/add")
    public String showAlertForm(Model model){
        model.addAttribute("alertForm", new AlertForm());
        return "alertForm";
    }
    @PostMapping("/user/alert/add")
    public String getAlertForm(Model model, @ModelAttribute @Valid  AlertForm alertForm, BindingResult bindingResult){
        if ( bindingResult.hasErrors()){
            model.addAttribute("alertInfo", "Some data is incorrect");
            return "alertForm";
        }
        alertService.addNewAlert(alertForm);
        return "redirect:/user/alerts";
    }
    @GetMapping("/alert/delete/{alertId}")
    public String deleteContact(@PathVariable("alertId") int id) {
        alertService.deleteAlert(id);
        return "redirect:/user/alerts";
    }


}
