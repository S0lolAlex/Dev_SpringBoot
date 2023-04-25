package org.greenSnake.entities;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Note implements Comparable{

    private long id;
    private String title;
    private String content;
    @Override
    public int compareTo(Object o) {
        if (o instanceof Note == false){
            return -1;
        }

        if (o == null){
            return -1;
        }

        Note note = (Note) o;
        if (note.getTitle().equals(getTitle()) && note.getContent().equals(getContent())){
            return 0;
        }
        return -1;
    }
}
