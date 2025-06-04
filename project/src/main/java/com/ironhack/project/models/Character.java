package com.ironhack.project.models;

import com.ironhack.project.enums.CharacterStatus;
import jakarta.persistence.*;

@Entity
public class Character {
    @Id
    private String name;
    private Integer level;
    @Enumerated(EnumType.STRING)
    private CharacterStatus Status;

    @ManyToOne
    @JoinColumn(name = "player_name")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "campaing_id")
    private Campaing campaing;

    public Character() {
    }

    public Character(String name, Integer level, CharacterStatus status, Player player, Campaing campaing) {
        this.name = name;
        this.level = level;
        Status = status;
        this.player = player;
        this.campaing = campaing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public CharacterStatus getStatus() {
        return Status;
    }

    public void setStatus(CharacterStatus status) {
        Status = status;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Campaing getCampaing() {
        return campaing;
    }

    public void setCampaing(Campaing campaing) {
        this.campaing = campaing;
    }
}
