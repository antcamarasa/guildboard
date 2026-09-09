package com.example.demo.controller;

import com.example.demo.dto.quest.request.CreateQuestRequest;
import com.example.demo.dto.quest.request.UpdateQuestRequest;
import com.example.demo.dto.quest.response.QuestResponse;
import com.example.demo.model.Quest;
import com.example.demo.service.QuestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuestController {
    private final QuestService questService;

    @Autowired
    public QuestController(QuestService questService){
        this.questService = questService;
    }

    @GetMapping("/quests")
    public List<QuestResponse> findAll(){
        List<QuestResponse> questRepons = new ArrayList<>();
        List<Quest> questsList = questService.findAll();

        for(Quest quest : questsList){
            questRepons.add(QuestResponse.from(quest));
        }

        return questRepons;
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
                                // Valid vérifie, la validation d'entrée dans UpdateQuestRequest.
                                // Request body => Parse le body.
                                @Valid @RequestBody UpdateQuestRequest updateQuestRequest){
        return questService.update(id, updateQuestRequest);
    }


    //DELETE /api/quests/{id} suppression (interdite si IN_PROGRESS)
    @DeleteMapping("quests/{id}")
    public void delete(@PathVariable Integer id){
        questService.delete(id);
    }

    // POST /api/quests/{id}/assignment
    // assigne un aventurier (corps : {"adventurerId": ... }), applique RG1 et RG2
    // RG1, niveau requis.
    // RG2, une quête à la fois.

    // POST /api/quests/{id}/completion // Termine la quête, on applique RG3
    // RG3, complétion et montée de niveau.
    // Complétion et montée de niveau : terminer une quête crédite goldReward et xpReward à l'aventurier, passe la quête en COMPLETED
    // et renseigne completedAt.

}

