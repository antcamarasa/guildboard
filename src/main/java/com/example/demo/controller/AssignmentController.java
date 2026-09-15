package com.example.demo.controller;

import com.example.demo.dto.assignment.response.AssignmentResponse;
import com.example.demo.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssignmentController {
    private final AssignmentService assignmentService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService){
        this.assignmentService = assignmentService;
    }

    // POST / Create Assignment / La route décrit l'intention métier
    @PostMapping("/adventurers/{adventurerId}/quests/{questId}")
    public AssignmentResponse create(@PathVariable Integer adventurerId,
                                     @PathVariable Integer questId){
        return assignmentService.save(adventurerId, questId);
    }

    @GetMapping("/assignments")
    public List<AssignmentResponse> getAll(){
        return assignmentService.findAll();
    }

    // Deprecated, la route vient de quest maintenant.
    // UPDATE / close assignment / increase gold, xp et level.
    @PostMapping("/assignment/{id}/complete")
    public AssignmentResponse complete(@PathVariable Integer id){
        return assignmentService.complete(id);
    }
}
