package pl.dariuszmedrala.organiser.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dariuszmedrala.organiser.models.entities.NoteEntity;
import pl.dariuszmedrala.organiser.models.forms.NoteForm;
import pl.dariuszmedrala.organiser.models.repositories.NoteRepository;
import pl.dariuszmedrala.organiser.models.sessions.UserSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    final UserSession userSession;
    final NoteRepository noteRepository;

    @Autowired
    public NoteService(UserSession userSession, NoteRepository noteRepository){
        this.userSession = userSession;
        this.noteRepository = noteRepository;
    }

    private LocalDate convertStringToLocalDate(String stringDate){
        LocalDate dateFromUserInput = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dateFromUserInput;
    }

    public void addNote(NoteForm noteForm) {
        NoteEntity newNote = new NoteEntity(noteForm, userSession, convertStringToLocalDate(noteForm.getNoteDate()));
        noteRepository.save(newNote);
    }

    public List<NoteEntity> getListOfNotesForToday(){
        List<NoteEntity> notesList = noteRepository.noteListForToday(
                userSession.getUserEntity().getLogin(), LocalDate.now()).stream()
                .sorted((s, s1) -> Integer.compare(Integer.getInteger(s1.getPriority()), Integer.getInteger(s.getPriority())))
                .collect(Collectors.toList());

        return notesList;
    }
}
