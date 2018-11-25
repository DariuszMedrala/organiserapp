package pl.dariuszmedrala.organiser.models.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class NoteForm {
    @Pattern(regexp = "[0-9]")
    private String priority;
    @Pattern (regexp = "[a-zA-Z0-9]{1,25}")
    private String title;
    @Pattern(regexp = "[a-zA-Z0-9]{1,160}")
    private String noteText;
    @Pattern(regexp = "[0-9]{4}-[0-1][0-9]-[0-3][0-9]")
    private String noteDate;
}
