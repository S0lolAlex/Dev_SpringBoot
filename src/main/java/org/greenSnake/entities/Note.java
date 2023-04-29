package org.greenSnake.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Note{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String title;
    @Column
    private String content;

}
