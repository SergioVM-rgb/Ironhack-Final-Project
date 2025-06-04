package com.ironhack.project.controllers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.project.controllers.dtos.CharacterLevelDTO;
import com.ironhack.project.controllers.dtos.CharacterStatusDTO;
import com.ironhack.project.enums.CharacterStatus;
import com.ironhack.project.models.Character;
import com.ironhack.project.models.Player;
import com.ironhack.project.repositories.CharacterRepository;
import com.ironhack.project.repositories.PlayerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CharacterControllerTest {
    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    Character character;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Optional<Player> playerOptional = playerRepository.findById("Pancho");
        assertTrue(playerOptional.isPresent());
        character = new Character("El_Gnomo", 6, CharacterStatus.DEAD, playerOptional.get(), null);
    }

    @AfterEach
    void tearDown() {
        characterRepository.deleteById("El_Gnomo");
    }

    @Test
    void getAllCharacters_validRequest_allCharacters() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/characters"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    void saveCharacter_validBody_characterSaved() throws Exception {
        String body = objectMapper.writeValueAsString(character);

        mockMvc.perform(post("/api/characters").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void updateCharacterLevel_validBody_characterUpdated() throws Exception {
        CharacterLevelDTO characterLevelDTO = new CharacterLevelDTO(2);

        String body = objectMapper.writeValueAsString(characterLevelDTO);

        mockMvc.perform(patch("/api/characters/El_Gnomo/level").content(body).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent())
            .andReturn();
    }

    @Test
    void updateCharacterStatus_validBody_characterUpdated() throws Exception {
        CharacterStatusDTO characterStatusDTO = new CharacterStatusDTO("ACTIVE");

        String body = objectMapper.writeValueAsString(characterStatusDTO);

        mockMvc.perform(patch("/api/characters/El_Gnomo/status").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void deleteCharacter_validRequest_characterDeleted() throws Exception {
        characterRepository.save(character);

        mockMvc.perform(delete("/api/characters/El_Gnomo"))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}