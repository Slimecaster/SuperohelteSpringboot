package com.example.superoheltespringboot.Controller;

import com.example.superoheltespringboot.Model.MarvelCharacter;
import com.example.superoheltespringboot.Service.MarvelCharacterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MarvelCharacterController
{
    private MarvelCharacterService marvelCharacterService;

    @GetMapping("/marvelcharacters")
    public String GetAllMarvelCharacters (Model model)
    {
    model.addAttribute("superhelte", marvelCharacterService.getAllMarvelCharacters());
    return "index";
    }
    @DeleteMapping("/marvelcharacter/delete")
    public String deleteMarvelCharacter (@PathVariable String alias)
    {
    marvelCharacterService.deleteMarvelCharacter(alias);
    return "redirect:/marvelcharacters";
    }
    @GetMapping("/marvelcharacter/add")
    public String showAddForm(Model model)
    {
    model.addAttribute("language", new MarvelCharacter());
    return "add-marvelCharacter";
    }
    @PostMapping("/marvelcharacter/save")
    public String saveMarvelCharacter (@ModelAttribute MarvelCharacter marvelCharacter)
    {
    marvelCharacterService.saveMarvelCharacter(marvelCharacter);
    return "redirect:/marvelcharacters";
    }
    @GetMapping("/character/{alias}")
    public String findByAlias (Model model, String alias)
    {
    model.addAttribute("superhelte", marvelCharacterService.findByAlias(alias));
    return "index";
    }
    @GetMapping("/marvelcharacter/edit/{alias}")
    public String showEditForm(@PathVariable String alias, Model model)
    {
    marvelCharacterService.findByAlias(alias).ifPresent(marvelcharacter -> model.addAttribute("marvelcharacter", marvelcharacter));
    return "edit-marvelcharacter";
    }

}
