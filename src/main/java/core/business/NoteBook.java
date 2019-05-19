package core.business;

import core.domain.Note;
import core.service.NotesService;
import core.utils.DateUtils;
import core.utils.NotesMapper;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NoteBook {
    @Setter
    private NotesService notesService;

    @Setter
    private Scanner inputScanner;

    private List<Note> getAll() {
        return notesService.getAll()
                .stream()
                .map(NotesMapper::map)
                .collect(Collectors.toList());
    }

    private List<Note> getByTitle(String title) {
        return notesService.getByTitle(title)
                .stream()
                .map(NotesMapper::map)
                .collect(Collectors.toList());
    }

    private List<Note> getByContent(String content) {
        return notesService.getByContent(content)
                .stream()
                .map(NotesMapper::map)
                .collect(Collectors.toList());
    }

    private List<Note> getByCreateDate(Date date) {
        return notesService.getByCreateDate(date)
                .stream()
                .map(NotesMapper::map)
                .collect(Collectors.toList());
    }

    private void addNote() {
        Note note = new Note();
        System.out.println("Введите заголовок");
        note.setTitle(inputScanner.next());
        System.out.println("Введите текст");
        note.setContent(inputScanner.next());
        note.setCreateDate(new Date());
        notesService.add(NotesMapper.reverseMap(note));
    }

    private void editNote() {
        System.out.println("Введите номер заметки, которую хотите изменить");
        String noteIdInput = inputScanner.next();
        Optional<core.domain.entity.Note> optionalNoteEntity = notesService.getById(Long.valueOf(noteIdInput));
        if (optionalNoteEntity.isPresent()) {
            core.domain.entity.Note noteEntity = optionalNoteEntity.get();
            System.out.println("Введите новый заголовок");
            noteEntity.setTitle(inputScanner.next());

            System.out.println("Введите новый текст");
            noteEntity.setContent(inputScanner.next());

            noteEntity.setCreateDate(new Date());
            notesService.update(noteEntity);
        } else {
            System.out.println("Заметки с таким номером не существует");
        }
    }

    private void deleteNote() {
        System.out.println("Введите номер заметки, которую хотите удалить");
        String noteIdInput = inputScanner.next();
        Optional<core.domain.entity.Note> optionalNoteEntity = notesService.getById(Long.valueOf(noteIdInput));
        if (optionalNoteEntity.isPresent()) {
            notesService.delete(optionalNoteEntity.get());
        } else {
            System.out.println("Заметки с таким номером не существует");
        }
    }

    private void showAllNotes() {
        getAll().forEach(System.out::println);
    }

    private void showByTitle() {
        System.out.println("Введите заголовок");
        String inputTitle = inputScanner.next();
        getByTitle(inputTitle).forEach(System.out::println);
    }

    private void showByContent() {
        System.out.println("Введите текст");
        String inputContent = inputScanner.next();
        getByContent(inputContent).forEach(System.out::println);
    }

    private void showByCreateDate() {
        System.out.println("Введите время в формате yyyy-MM-dd");
        String inputDate = inputScanner.next();
        try {
            DateTime dateTime = DateUtils.parse(inputDate);
            getByCreateDate(dateTime.toDate()).forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный ввод");
        }
    }

    private boolean executeCommand(String command) {
        switch (command) {
            case "add": {
                addNote();
                return true;
            }
            case "edit": {
                editNote();
                return true;
            }
            case "delete": {
                deleteNote();
                return true;
            }
            case "title": {
                showByTitle();
                return true;
            }
            case "content": {
                showByContent();
                return true;
            }
            case "date": {
                showByCreateDate();
                return true;
            }
        }
        return false;
    }

    public void lifeCycle() {
        boolean live = true;
        while (live) {
            showAllNotes();
            System.out.println("Введите команду");
            System.out.println("stop - остановка программы");
            System.out.println("add - добавить заметку");
            System.out.println("edit - изменить заметку");
            System.out.println("delete - удалить заметку");
            System.out.println("title - найти заметки по заголовку");
            System.out.println("content - найти заметки по тексту");
            System.out.println("date - найти заметки по дате создания");
            String command = inputScanner.next();
            if (!command.equals("stop")) {
                if (!executeCommand(command)) {
                    System.out.println("Вы ввели некорректную команду");
                }
            } else {
                live = false;
            }
        }
    }
}
