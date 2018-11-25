package pl.dariuszmedrala.organiser.models.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dariuszmedrala.organiser.models.sessions.UserSession;
import pl.dariuszmedrala.organiser.models.forms.NoteForm;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "note")
@NoArgsConstructor
public class NoteEntity {

    @GeneratedValue
    @Id
    private int id;
    @Column(name="user_id")
    private int userId;
    private String priority;
    private String title;
    private String text;
    private LocalDate noteDate;

    public NoteEntity(NoteForm noteForm, UserSession userSession, LocalDate noteDate){
        this.userId = userSession.getUserEntity().getId();
        this.priority = noteForm.getPriority();
        this.title = noteForm.getTitle();
        this.text = noteForm.getNoteText();
        this.noteDate = noteDate;
    }
}
