package pl.dariuszmedrala.organiser.models;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.dariuszmedrala.organiser.models.entities.AlertEntity;
import pl.dariuszmedrala.organiser.models.repositories.AlertRepository;
import pl.dariuszmedrala.organiser.models.services.AlertService;
import pl.dariuszmedrala.organiser.models.services.SmsService;

import java.time.LocalDateTime;

@Component
@Log
public class AlertJob {

    final AlertService alertService;
    final SmsService smsService;
    final AlertRepository alertRepository;
    @Autowired
    public AlertJob(AlertService alertService, SmsService smsService, AlertRepository alertRepository) {
        this.alertService = alertService;
        this.smsService = smsService;
        this.alertRepository = alertRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void sendSms(){
        for (AlertEntity alert: alertService.findAllAlerts()) {
            if((alert.getDate().isEqual(LocalDateTime.now().withSecond(0).withNano(0)))){
                smsService.sendSms(alert.getPhoneNumber(), alert.getMessage());
            }
        }
    }
}
