package core.utils;

import core.domain.Note;

public class NotesMapper {
    public static Note map(core.domain.entity.Note note) {
        Note result = new Note();
        result.setId(note.getId());
        result.setTitle(note.getTitle());
        result.setContent(note.getContent());
        result.setCreateDate(note.getCreateDate());
        return result;
    }

    public static core.domain.entity.Note reverseMap(Note note) {
        core.domain.entity.Note result = new core.domain.entity.Note();
        result.setId(note.getId());
        result.setTitle(note.getTitle());
        result.setContent(note.getContent());
        result.setCreateDate(note.getCreateDate());
        return result;
    }
}
