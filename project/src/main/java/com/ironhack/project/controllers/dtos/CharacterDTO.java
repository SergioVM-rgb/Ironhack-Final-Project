package com.ironhack.project.controllers.dtos;

import com.ironhack.project.enums.CharacterStatus;
import jakarta.validation.constraints.NotEmpty;

public class CharacterDTO {
    @NotEmpty(message = "Name can't be empty or null")
    private String name;
    @NotEmpty(message = "Level can't be empty or null")
    private int level;
    @NotEmpty(message = "Status can't be empty or null")
    private String status;
    @NotEmpty(message = "Insert the name of the player that plays this character")
    private String playerId;
    private Integer campaingId;

    public CharacterDTO() {
    }

    public CharacterDTO(String name, int level, CharacterStatus characterStatus, String playerId, int campaingId) {
        this.name = name;
        this.level = level;
        this.status = characterStatus.toString();
        this.playerId = playerId;
        this.campaingId = campaingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public CharacterStatus getCharacterStatus() {
        return CharacterStatus.valueOf(status);
    }

    public void setCharacterStatus(CharacterStatus characterStatus) {
        this.status = characterStatus.toString();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Integer getCampaingId() {
        return campaingId;
    }

    public void setCampaingId(Integer campaingId) {
        this.campaingId = campaingId;
    }
}
