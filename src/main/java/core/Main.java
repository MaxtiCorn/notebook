package core;

import core.business.NoteBook;
import core.dao.NotesDaoImpl;
import core.service.NotesServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NotesServiceImpl notesService = new NotesServiceImpl();
        NotesDaoImpl notesDao = new NotesDaoImpl();
        notesService.setNotesDao(notesDao);

        NoteBook noteBook = new NoteBook();
        noteBook.setInputScanner(new Scanner(System.in));
        noteBook.setNotesService(notesService);
        noteBook.lifeCycle();
    }
}
