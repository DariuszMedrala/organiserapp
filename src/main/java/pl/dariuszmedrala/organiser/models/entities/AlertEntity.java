package pl.dariuszmedrala.organiser.models.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dariuszmedrala.organiser.models.forms.AlertForm;
import pl.dariuszmedrala.organiser.models.sessions.UserSession;

import javax.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name="alert")
@Data
@NoArgsConstructor
public class AlertEntity {
    @Id
    @GeneratedValue
    private int id;
    @Column(name="phone_number")
    private String phoneNumber;
    private String message;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    public AlertEntity(AlertForm alertForm, UserSession userSession, LocalDateTime date ){
        this.phoneNumber = alertForm.getPhoneNumber();
        this.date = date;
        this.message = alertForm.getMessage();
        this.userId = userSession.getUserEntity();

    }
}
