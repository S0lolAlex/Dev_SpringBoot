package org.greenSnake.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Note implements Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Note)){
            return -1;
        }

        Note note = (Note) o;
        if (note.getTitle().equals(getTitle()) && note.getContent().equals(getContent())){
            return 0;
        }
        return -1;
    }

}
