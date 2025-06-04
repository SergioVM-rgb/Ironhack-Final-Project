package com.ironhack.project.services.interfaces;

import com.ironhack.project.controllers.dtos.CharacterDTO;
import com.ironhack.project.controllers.dtos.CharacterLevelDTO;
import com.ironhack.project.controllers.dtos.CharacterStatusDTO;
import com.ironhack.project.models.Character;

import java.util.List;

public interface ICharacterService {
    Character create(CharacterDTO characterDTO);
    void levelUp(String name, CharacterLevelDTO characterLevelDTO);
    void updateStatus(String name, CharacterStatusDTO statusDTO);
    void delete(String name);
    List<Character> getAll();
}
