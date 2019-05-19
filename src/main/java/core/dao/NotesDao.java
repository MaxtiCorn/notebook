package core.dao;

import core.domain.entity.Note;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface NotesDao {
    void add(Note note);

    void delete(Note note);

    void update(Note note);

    List<Note> getAll();

    List<Note> getByTitle(String title);

    List<Note> getByContent(String content);

    List<Note> getByCreateDate(Date date);

    Optional<Note> getById(Long id);
}
