package com.ironhack.project.repositories;

import com.ironhack.project.enums.CharacterStatus;
import com.ironhack.project.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, String> {
    //Finds all character with the given status
    List<Character> findByLevel(Integer level);
    List<Character> findByStatus(CharacterStatus status);
    List<Character> findByLevelAndStatus(Integer level, CharacterStatus status);
}
