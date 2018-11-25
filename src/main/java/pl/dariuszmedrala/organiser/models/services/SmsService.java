package pl.dariuszmedrala.organiser.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.smsapi.OAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.exception.ClientException;
import pl.smsapi.exception.SmsapiException;

@Service
public class SmsService {
    final AlertService alertService;

    @Autowired
    public SmsService(AlertService alertService) {
        this.alertService = alertService;
    }

    public void sendSms(String phoneNumber, String message) {
        try {
            OAuthClient newClient = new OAuthClient("WC83iaQCB0eDPxQSOD4dUgsEb4BjUmjXirnHQZcY");
            SmsFactory smsApi = new SmsFactory(newClient);
            SMSSend action = smsApi.actionSend().setText(message).setTo(phoneNumber);
            action.execute();
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (SmsapiException e) {
            e.printStackTrace();
        }
    }
}
