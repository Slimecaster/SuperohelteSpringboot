package com.example.superoheltespringboot.Service;

import com.example.superoheltespringboot.Controller.MarvelCharacterController;
import com.example.superoheltespringboot.Model.MarvelCharacter;
import com.example.superoheltespringboot.Repository.MarvelCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarvelCharacterService
{
    private MarvelCharacterRepository characterRepository;

    public void saveMarvelCharacter(MarvelCharacter marvelCharacter)
    {
        characterRepository.save(marvelCharacter);
    }

    public List<MarvelCharacter> getAllMarvelCharacters()
    {
        return characterRepository.findAll();
    }
    public Optional findByAlias (String alias)
    {
        return Optional.ofNullable(characterRepository.findByAlias(alias));
    }

    public void deleteMarvelCharacter(String alias)
    {
        characterRepository.delete(alias);
    }
}
