package com.ironhack.project.services.impl;

import com.ironhack.project.controllers.dtos.CharacterDTO;
import com.ironhack.project.controllers.dtos.CharacterLevelDTO;
import com.ironhack.project.controllers.dtos.CharacterStatusDTO;
import com.ironhack.project.models.Character;
import com.ironhack.project.models.Player;
import com.ironhack.project.repositories.CampaingRepository;
import com.ironhack.project.repositories.CharacterRepository;
import com.ironhack.project.repositories.PlayerRepository;
import com.ironhack.project.services.interfaces.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService implements ICharacterService {
    @Autowired
    public CharacterRepository characterRepository;

    @Autowired
    public PlayerRepository playerRepository;

    @Autowired
    public CampaingRepository campaingRepository;

    //This method stores a new character in the system
    public Character create(CharacterDTO characterDTO) {
        //Check if the player in the CharacterDTO exists
        Optional<Player> player = playerRepository.findById(characterDTO.getPlayerId());
        if (player.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no player with that name");
        }
        //Check if the character already exists
        Optional<Character> character = characterRepository.findById(characterDTO.getName());
        if (character.isEmpty()) {
            try {
                //Create a new character from the DTO
                Character newCharacter = new Character(characterDTO.getName(), characterDTO.getLevel(), characterDTO.getCharacterStatus(), player.get(), campaingRepository.getReferenceById(characterDTO.getCampaingId()));
                //Save it
                return characterRepository.save(newCharacter);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Input not correct, please review the data and try again");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Character already exists in the system");
        }
    }

    public List<Character> getAll() {
        return characterRepository.findAll();
    }

    public void levelUp(String name, CharacterLevelDTO characterLevelDTO) {
        //Check if the character already exists
        Optional<Character> character = characterRepository.findById(name);
        if (character.isPresent()) {
            try {
                //Update the level of the character
                character.get().setLevel(characterLevelDTO.getLevel());
                //Save the changes
                characterRepository.save(character.get());
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Level not valid, it's just a number buddy");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The character doesn't exist");
        }
    }

    public void updateStatus(String name, CharacterStatusDTO statusDTO) {
        //Check if the character already exists
        Optional<Character> character = characterRepository.findById(name);
        if (character.isPresent()) {
            try {
                //Update the level of the character
                character.get().setStatus(statusDTO.getCharacterStatus());
                //Save the changes
                characterRepository.save(character.get());
            } catch (Exception e) {
                // Throw an exception if the status value is not valid
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status has to be either ACTIVE, ON_HOLD, RETIRED OR DEAD");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The character doesn't exist.");
        }
    }

    public void delete(String name) {
        characterRepository.deleteById(name);
    }
}
