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

    @GetMapping("/quests")
    public List<Quest> all(){
        return questService.findAll();
    }

    @GetMapping("/quests/{id}")
    public Quest show(@PathVariable Long id){
        return questService.findById(id);
    }

    @PostMapping("/quests")
    public Quest create(@RequestBody Quest quest){
        return questService.create(quest);
    }

    @PutMapping("/quests/{id}")
    public Quest update(@PathVariable Long id, @RequestBody Quest quest){
        return questService.update(id, quest);
    }

    @DeleteMapping("/quests/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        questService.delete(id);
    }
}