package org.greenSnake.Service;

import lombok.RequiredArgsConstructor;
import org.greenSnake.entities.Note;
import org.greenSnake.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteService {
    @Autowired
    private final NoteRepository repository;
    public List<Note> listAll(){
        return repository.findAll();
    }
    public Note add(Note note){
            return repository.save(note);
    }
    public void deleteById(long id){
            repository.deleteById(id);
    }
    public void update(Note note){
            repository.saveAndFlush(note);
    }
    public Note getById(long id) {
        return repository.findById(id).orElse(null);
    }
}
