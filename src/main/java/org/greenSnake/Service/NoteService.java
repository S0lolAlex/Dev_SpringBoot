package org.greenSnake.Service;

import org.greenSnake.entities.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class NoteService {
    private Map<Long, Note> listNote = new HashMap<>();
    public List<Note> listAll(){
        return new ArrayList<>(listNote.values());
    }
    public Note add(Note note){
        listNote.put(note.getId(),note);
        return listNote.get(note.getId());
    }
    public void deleteById(long id){
        try{listNote.remove(id);
        }catch (NullPointerException e){
            throw new NullPointerException("note not found");
        }
    }
    public void update(Note note){
        if(listNote.containsKey(note.getId())){
            Note upNote = listNote.get(note.getId());
            upNote.setTitle(note.getTitle());
            upNote.setContent(note.getContent());
        }else {
            throw new NullPointerException("note not found");
        }
    }
    public Note getById(long id){
        Note note;
        try{note = listNote.get(id);
        }catch (NullPointerException e){
            throw new NullPointerException("note not found");
        }
        return note;
    }

}
