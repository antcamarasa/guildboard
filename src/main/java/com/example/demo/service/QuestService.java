package com.example.demo.service;

import com.example.demo.model.Quest;
import com.example.demo.model.enums.Status;
import com.example.demo.repository.QuestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service//cest un service spring, il sera injecté dans le controller

public class QuestService {

    private final QuestRepository questRepository;

    public QuestService(QuestRepository questRepository){
        this.questRepository = questRepository;
    }

    @Transactional(readOnly = true)// je ne modifie pas la base de donnée, je ne fais que lire
    public List<Quest> findAll(){
        return questRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Quest findById(Integer id){
        return questRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quête introuvable avec l'id : " + id));// si je ne trouve pas la quête, je lance une exception
    }

    @Transactional
    public Quest create(Quest quest){// je crée une nouvelle quête dans ma bdd
        quest.setStatus(Status.AVAILABLE);// je set le status de la quête à AVAILABLE avant de la sauvegarder
        return questRepository.save(quest);// je sauvegarde la quête dans la base de donnée et je retourne l'objet sauvegardé
    }

    @Transactional
    public void delete(Integer id){
         // 1. Est-ce que la quête existe ?
        Quest quest = questRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Quête introuvable avec l'id : " + id));


         // 2. Est-ce qu'on a le droit de la supprimer ?
        if(quest.getStatus() == Status.IN_PROGRESS){
             throw new RuntimeException("Impossible de supprimer une quête en cours !");
        }

         // 3. On supprime
        questRepository.delete(quest);
    }

    @Transactional
    public Quest update(Integer id, Quest questModifiee){

        // 1. Est-ce que la quête existe ?
        Quest questExistante = questRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quête introuvable avec l'id : " + id));

    // 2. Interdit si IN_PROGRESS ou COMPLETED
        if(questExistante.getStatus() == Status.IN_PROGRESS || questExistante.getStatus() == Status.COMPLETED){
            throw new RuntimeException("Impossible de modifier cette quête !");
    }

    // 3. On remplace les champs
    questExistante.setTitle(questModifiee.getTitle());
    questExistante.setDescription(questModifiee.getDescription());
    questExistante.setDifficulty(questModifiee.getDifficulty());
    questExistante.setRequiredLevel(questModifiee.getRequiredLevel());
    questExistante.setGoldReward(questModifiee.getGoldReward());
    questExistante.setXpReward(questModifiee.getXpReward());

    return questRepository.save(questExistante);
}
}