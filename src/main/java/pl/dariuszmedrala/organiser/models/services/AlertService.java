package pl.dariuszmedrala.organiser.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dariuszmedrala.organiser.models.entities.AlertEntity;
import pl.dariuszmedrala.organiser.models.forms.AlertForm;
import pl.dariuszmedrala.organiser.models.repositories.AlertRepository;
import pl.dariuszmedrala.organiser.models.sessions.UserSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AlertService {
    final AlertRepository alertRepository;
    final NoteService noteService;
    final UserSession userSession;

    @Autowired
    public AlertService(AlertRepository alertRepository, NoteService noteService, UserSession userSession) {
        this.alertRepository = alertRepository;
        this.noteService = noteService;
        this.userSession = userSession;

    }

    public void addNewAlert(AlertForm alertForm ) {

        AlertEntity newAlert = new AlertEntity(alertForm, userSession, convertStringToLocalDate(alertForm.getDate()+ " " +alertForm.getHour() +":00") );
        alertRepository.save(newAlert);
        ;
    }
    private LocalDateTime convertStringToLocalDate(String stringDate){
        LocalDateTime dateFromUserInput = LocalDateTime.parse(stringDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return dateFromUserInput;
    }
    public List<AlertEntity> findAllByLogin(String login){
        return alertRepository.findAllByUserLogin(userSession.getUserEntity().getLogin());
    }


    public List<AlertEntity> findAllAlerts(){
        return alertRepository.findAllAlerts();
    }
    public void deleteAlert(int id) {
        if (alertRepository.findAllByUserLogin(userSession.getUserEntity().getLogin())
                .stream()
                .anyMatch(s->s.getId() == id)) {
            alertRepository.deleteById(id);
        }
    }
}