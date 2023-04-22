import org.greenSnake.Data.Note;
import org.greenSnake.Service.NoteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NoteServiceTest {
    private NoteService service = new NoteService();
    private Note getAnyNote() {
        return service.listAll().stream().findAny().orElse(null);
    }
    private long count = 1L;
    @BeforeEach
    void setUp() {
        Note note = new Note();
        note.setId(count++);
        note.setContent("some text");
        note.setTitle("easy title");
        service.add(note);
    }

    @Test
    public void testThatListNotNull(){
        Assertions.assertNotNull(service.listAll());
    }

    @Test
    public void testCorrectAdd(){
        Note note = new Note();
        note.setContent("testContent");
        note.setTitle("Title");
        Assertions.assertEquals(note,service.add(note));
    }
    @Test
    public void testThatGetByIdCorrect(){
        Note note = getAnyNote();
        long id = note.getId();
        Assertions.assertEquals(service.getById(id),note);
    }

    @Test
    public void testThatNoteDelete(){
        long id = getAnyNote().getId();
        service.deleteById(id);
        Assertions.assertNull(service.getById(id));
    }

    @Test
    public void testNoteUpdate(){
        Note note  = getAnyNote();
        note.setContent("newContent");
        service.update(note);
        String upContent = service.getById(note.getId()).getContent();
        Assertions.assertEquals("newContent",upContent);
    }
}
