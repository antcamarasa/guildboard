package com.example.demo.controller;

import com.example.demo.dto.adventurer.AdventurerResponse;
import com.example.demo.dto.adventurer.CreateAdventurerRequest;
import com.example.demo.dto.adventurer.UpdateAdventurerRequest;
import com.example.demo.dto.assignment.response.AssignmentResponse;
import com.example.demo.service.AdventurerService;
import com.example.demo.service.AssignmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdventurerController {
    private final AdventurerService adventurerService;
    private final AssignmentService assignmentService;

    @Autowired
    public AdventurerController(AdventurerService adventurerService, AssignmentService assignmentService){
        this.adventurerService = adventurerService;
        this.assignmentService = assignmentService;
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

    // Get /api/adventurers/{id}/history
    @GetMapping("/adventurers/{id}/history")
    public List<AssignmentResponse> showAdventurerAssignmentHistory(@PathVariable Integer id){
        return assignmentService.findByAdventurerId(id);
    }

    // POST /api/adventurers création
    @PostMapping("/adventurers")
    public AdventurerResponse createAdventurer(@Valid @RequestBody CreateAdventurerRequest createAdventurerRequest){
        return adventurerService.save(createAdventurerRequest);
    }

    // PUT /api/adventurers/{id} modification
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

}
