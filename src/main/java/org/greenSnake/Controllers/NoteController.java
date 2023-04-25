package org.greenSnake.Controllers;

import lombok.RequiredArgsConstructor;
import org.greenSnake.Service.NoteService;
import org.greenSnake.entities.Note;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService service = new NoteService();
    @GetMapping("/list")
    public ModelAndView getAllNotes(){
        ModelAndView result = new ModelAndView("note/list");
        result.addObject("notes", service.listAll());
        return result;
    }
    @GetMapping("/add")
    public ModelAndView addNote() {
        ModelAndView result = new ModelAndView("note/note");
        Note note = Note.builder().build();
        result.addObject("note", service.add(note));
        return result;
    }
    @GetMapping("/edit")
    public ModelAndView editNote(@RequestParam(value = "id")long id){
        ModelAndView result = new ModelAndView("note/note");
        result.addObject("note", service.getById(id));
        return result;
    }
    @PostMapping("/edit")
    public RedirectView editNoteView(@RequestParam(value = "id")long id,
                                     @RequestParam(value = "content")String content,
                                     @RequestParam(value = "title")String title){
        Note note = null;
        if (id == 0) {
            note = Note.builder().title(title).content(content).build();
            note.setId(id);
            note.setTitle(title);
            note.setContent(content);
            service.add(note);
        } else {
            note = service.getById(id);
            note.setTitle(title);
            note.setContent(content);
            service.update(note);
        }
        return new RedirectView("/note/list");
    }

    @PostMapping("/delete")
    public RedirectView deleteNoteById(@RequestParam(value = "id")long id){
        service.deleteById(id);
        return new RedirectView("/note/list");
    }
}
