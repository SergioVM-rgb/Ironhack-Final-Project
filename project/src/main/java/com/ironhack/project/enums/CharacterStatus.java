package com.ironhack.project.enums;


public enum CharacterStatus {
    /*
    Character is being played in a current campaing
     */
    ACTIVE,
    /*
    Character is not being played right now
     */
    ON_HOLD,
    /*
    Character has been relegated to npc status either due to satisfactory ending, loss of drive or just circumstances
     */
    RETIRED,
    /*
    Character has died and is no longer playable, at least most of the time
     */
    DEAD
}