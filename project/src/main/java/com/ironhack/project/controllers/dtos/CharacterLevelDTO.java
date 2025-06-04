package com.ironhack.project.controllers.dtos;

import jakarta.validation.constraints.NotEmpty;

public class CharacterLevelDTO {
    @NotEmpty(message = "Level can't be empty or null")
    private int level;

    public CharacterLevelDTO(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
