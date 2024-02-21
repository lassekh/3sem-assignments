package org.jpa.task1;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class NoteNameDTO {
    private Note note;
    private String name;
}
