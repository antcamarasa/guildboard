package com.example.demo.controller;

import com.example.demo.dto.quest.request.CreateQuestRequest;
import com.example.demo.dto.quest.request.UpdateQuestRequest;
import com.example.demo.dto.quest.response.QuestResponse;
import com.example.demo.model.Quest;
import com.example.demo.model.enums.Difficulty;
import com.example.demo.model.enums.Status;
import com.example.demo.service.QuestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class QuestController {
    private final QuestService questService;

    @Autowired
    public QuestController(QuestService questService){
        this.questService = questService;
    }

    @GetMapping("/quests")
    public List<QuestResponse> findAll(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false)Difficulty difficulty)
    {
        return questService.findAll(status, difficulty);
    }

    @GetMapping("/quests/{id}")
    public QuestResponse findById(@PathVariable Integer id){
        Quest quest = questService.findById(id);
        return QuestResponse.from(quest);
    }

    // POST /api/quests création (statut AVAILABLE)
    @PostMapping("/quests")
    public QuestResponse create(@RequestBody CreateQuestRequest createQuestRequest){
        return questService.save(createQuestRequest);
    }

    @PutMapping("quests/{id}")
    public QuestResponse update(@PathVariable Integer id,
                                @Valid @RequestBody UpdateQuestRequest updateQuestRequest){
        return questService.update(id, updateQuestRequest);
    }

    @DeleteMapping("quests/{id}")
    public void delete(@PathVariable Integer id){
        questService.delete(id);
    }

}

