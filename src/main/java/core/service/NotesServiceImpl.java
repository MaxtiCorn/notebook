package core.service;

import core.dao.NotesDao;
import core.domain.entity.Note;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class NotesServiceImpl implements NotesService {
    @Setter
    private NotesDao notesDao;

    @Override
    public void add(Note note) {
        notesDao.add(note);
    }

    @Override
    public void delete(Note note) {
        notesDao.delete(note);
    }

    @Override
    public void update(Note note) {
        notesDao.update(note);
    }

    @Override
    public Optional<Note> getById(Long id) {
        return notesDao.getById(id);
    }

    @Override
    public List<Note> getAll() {
        return notesDao.getAll();
    }

    @Override
    public List<Note> getByTitle(String title) {
        return notesDao.getByTitle(title);
    }

    @Override
    public List<Note> getByContent(String content) {
        return notesDao.getByContent(content);
    }

    @Override
    public List<Note> getByCreateDate(Date date) {
        return notesDao.getByCreateDate(date);
    }
}
