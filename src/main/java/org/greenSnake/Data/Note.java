package org.greenSnake.Data;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", length = 100, nullable = false)
    private String title;
    @Column(name = "content", length = 500)
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note note)) return false;

        if (!getTitle().equals(note.getTitle())) return false;
        return getContent() != null ? getContent().equals(note.getContent()) : note.getContent() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        return result;
    }
}
