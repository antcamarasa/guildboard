package com.example.demo.controller;

import com.example.demo.model.Quest;
import com.example.demo.service.QuestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestController {

    private final QuestService questService;


    public QuestController(QuestService questService){
        this.questService = questService;
    }

    // GET /api/quests → liste de toutes les quêtes
    @GetMapping("/quests")
    public List<Quest> all(){
        return questService.findAll();
    }

    // GET /api/quests/{id} cela détail une quête
    @GetMapping("/quests/{id}")
    public Quest show(@PathVariable Integer id){
        return questService.findById(id);
    }

    // POST /api/quests sa créer une quête
    @PostMapping("/quests")
    public Quest create(@RequestBody Quest quest){
        return questService.create(quest);
    }

    // PUT /api/quests/{id} c pour modifier une quête
    @PutMapping("/quests/{id}")
    public Quest update(@PathVariable Integer id, @RequestBody Quest quest){
        return questService.update(id, quest);
    }

    //delete api quests/{id} sa supprime une quête
    @DeleteMapping("/quests/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        questService.delete(id);
    }

}