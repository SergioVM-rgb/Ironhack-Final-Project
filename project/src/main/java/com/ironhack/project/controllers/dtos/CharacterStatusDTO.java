package com.ironhack.project.controllers.dtos;

import com.ironhack.project.enums.CharacterStatus;
import jakarta.validation.constraints.NotEmpty;

public class CharacterStatusDTO {
    @NotEmpty(message = "Status can't be empty or null")
    private String status;

    public CharacterStatusDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CharacterStatus getCharacterStatus() {
        return CharacterStatus.valueOf(status);
    }

    public void setCharacterStatus(CharacterStatus characterStatus) {
        this.status = characterStatus.toString();
    }
}
