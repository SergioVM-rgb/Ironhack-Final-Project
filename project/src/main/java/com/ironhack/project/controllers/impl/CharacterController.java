package com.ironhack.project.controllers.impl;

import com.ironhack.project.controllers.dtos.CharacterDTO;
import com.ironhack.project.controllers.dtos.CharacterLevelDTO;
import com.ironhack.project.controllers.dtos.CharacterStatusDTO;
import com.ironhack.project.enums.CharacterStatus;
import com.ironhack.project.models.Character;
import com.ironhack.project.repositories.CharacterRepository;
import com.ironhack.project.services.interfaces.ICharacterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CharacterController {
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ICharacterService characterService;


    // Retrieves a list of characters that match the search parameters
    @GetMapping("/characters")
    @ResponseStatus(HttpStatus.OK)
    public List<Character> search(@RequestParam Optional<Integer> level, @RequestParam Optional<CharacterStatus> status) {
        //If both are entered return characters with matching data
        if (level.isPresent() && status.isPresent()) {
            return characterRepository.findByLevelAndStatus(level.get(), status.get());
        }
        if (level.isPresent()) {
            //If only level is entered
            return characterRepository.findByLevel(level.get());
        } else if (status.isPresent()) {
            //If only status is entered
            return characterRepository.findByStatus(status.get());
        } else {
            //If no data is entered
            return characterRepository.findAll();
        }
    }

    @PostMapping("/characters")
    @ResponseStatus(HttpStatus.CREATED)
    public Character create(@RequestBody @Valid CharacterDTO characterDTO) {
        return characterService.create(characterDTO);
    }

    @PatchMapping("/characters/{name}/level")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void levelUp(@PathVariable String name, @RequestBody CharacterLevelDTO characterLevelDTO) {
        characterService.levelUp(name, characterLevelDTO);
    }

    @PatchMapping("/characters/{name}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable String name, @RequestBody CharacterStatusDTO characterStatusDTO) {
        characterService.updateStatus(name, characterStatusDTO);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Valid String name) {
        characterService.delete(name);
    }
}
