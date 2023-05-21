package org.greenSnake.Service;

import org.greenSnake.entities.Note;
import org.greenSnake.repository.NoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;


@SpringBootTest
@ComponentScan("org.greenSnake")
public class NoteServiceTest {
    NoteService service;
    @Autowired
    NoteRepository repository;

    @BeforeEach
    void beforeEach() {
        service = new NoteService(repository);
        Note note = new Note();
        note.setTitle("title1");
        note.setContent("same text");
        service.add(note);
    }

    @Test
    public void testThatListNotNull() {
        Assertions.assertNotNull(service.listAll());
    }

    @Test
    @Order(1)
    public void testCorrectAdd() {
        Note note = new Note();
        note.setContent("addTest");
        note.setTitle("Testing");
        Assertions.assertEquals(note, service.add(note));
    }

    @Test
    @Order(2)
    public void testThatGetByIdCorrect() {
        Note note = service.listAll().stream().findAny().orElse(null);
        long id = note.getId();
        Assertions.assertEquals(service.getById(id), note);
    }

    @Test
    @Order(4)
    public void testThatNoteDelete() {
        long id = service.listAll().stream().findAny().orElse(null).getId();
        service.deleteById(id);
        Assertions.assertNull(service.getById(id));
    }

    @Test
    @Order(3)
    public void testNoteUpdate() {
        Note note = service.listAll().stream().findAny().orElse(null);
        note.setContent("newContent");
        service.update(note);
        String upContent = service.getById(note.getId()).getContent();
        Assertions.assertEquals("newContent", upContent);
    }
}
