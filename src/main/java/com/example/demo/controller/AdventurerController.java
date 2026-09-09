package com.example.demo.controller;

import com.example.demo.dto.adventurer.AdventurerResponse;
import com.example.demo.dto.adventurer.CreateAdventurerRequest;
import com.example.demo.dto.adventurer.UpdateAdventurerRequest;
import com.example.demo.service.AdventurerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdventurerController {
    private final AdventurerService adventurerService;

    @Autowired
    public AdventurerController(AdventurerService adventurerService){
        this.adventurerService = adventurerService;
    }
    //GET /api/adventurers liste des aventuriers
    @GetMapping("/adventurers")
    public List<AdventurerResponse> all(){
        return adventurerService.findAll();
    }

    //GET /api/adventurers/{id} détail d'un aventurier
    @GetMapping("/adventurers/{id}")
    public AdventurerResponse showAdventurer(@PathVariable Integer id){
        return adventurerService.findById(id);
    }

    // POST /api/adventurers création
    @PostMapping("/adventurers")
    public AdventurerResponse createAdventurer(@Valid @RequestBody CreateAdventurerRequest createAdventurerRequest){
        return adventurerService.save(createAdventurerRequest);
    }

    // PUT /api/adventurers/{id} modification
    // id => Qui modifier
    // adventurerRequest => par quoi modifier.
    @PutMapping("/adventurers/{id}")
    public AdventurerResponse updateAdventurer(@PathVariable Integer id, @Valid @RequestBody UpdateAdventurerRequest adventurerRequest){
        return adventurerService.update(id, adventurerRequest);
    }

    // DELETE /api/adventurers/{id} suppression
    @DeleteMapping("/adventurers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdventurer(@PathVariable Integer id){
        adventurerService.delete(id);
    }

    // GET /api.adventurers/{id}/history
    //assignations passées et en cours de l'aventurier
}
