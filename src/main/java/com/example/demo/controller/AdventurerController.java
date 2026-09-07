package com.example.demo.controller;

import com.example.demo.dto.adventurer.AdventurerResponse;
import com.example.demo.dto.adventurer.CreateAdventurerRequest;
import com.example.demo.model.Adventurer;
import com.example.demo.service.AdventurerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdventurerController {
    private final AdventurerService adventurerService;

    public AdventurerController(AdventurerService adventurerService){
        this.adventurerService = adventurerService;
    }

    @GetMapping("/adventurers")
    public List<Adventurer> all(){
        return adventurerService.findAll();
    }

    @PostMapping("/adventurers")
    public AdventurerResponse createAdventurer(@RequestBody CreateAdventurerRequest createAdventurerRequest){
        return adventurerService.save(createAdventurerRequest);
    }
}
