package com.ironhack.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Player {
    @Id
    private String name;
    private String contactNumber;

    @OneToMany(mappedBy = "player")
    private List<Character> charactersCreated;

    public Player() {
    }

    public Player(String name, String contactNumber, List<Character> charactersCreated) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.charactersCreated = charactersCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<Character> getCharactersCreated() {
        return charactersCreated;
    }

    public void setCharactersCreated(List<Character> charactersCreated) {
        this.charactersCreated = charactersCreated;
    }
}
