package com.ironhack.project.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Campaing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int recLevel;

    @OneToMany(mappedBy = "campaing")
    private List<Character> charactersPlayed;

    public Campaing() {
    }

    public Campaing(String name, int recLevel, List<Character> charactersPlayed) {
        this.name = name;
        this.recLevel = recLevel;
        this.charactersPlayed = charactersPlayed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecLevel() {
        return recLevel;
    }

    public void setRecLevel(int recLevel) {
        this.recLevel = recLevel;
    }

    public List<Character> getCharactersPlayed() {
        return charactersPlayed;
    }

    public void setCharactersPlayed(List<Character> charactersPlayed) {
        this.charactersPlayed = charactersPlayed;
    }
}
