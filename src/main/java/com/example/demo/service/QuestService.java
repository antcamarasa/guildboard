package com.example.demo.service;

import com.example.demo.constant.Constant;
import com.example.demo.dto.quest.request.CreateQuestRequest;
import com.example.demo.dto.quest.request.UpdateQuestRequest;
import com.example.demo.dto.quest.response.QuestResponse;
import com.example.demo.exception.Quest.QuestDuplicateTitleException;
import com.example.demo.exception.Quest.QuestNotFoundException;
import com.example.demo.model.Quest;
import com.example.demo.repository.QuestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestService {
    // Fields
    private final QuestRepository questRepository;

    // Constructor
    public QuestService(QuestRepository questRepository){
        this.questRepository = questRepository;
    }

    // Methode
    @Transactional(readOnly = true)
    public List<Quest> findAll(){
        return questRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Quest findById(Integer id){
        return questRepository.findById(id).orElseThrow(() -> new QuestNotFoundException(Constant.QUEST_NOT_FIND_IN_DB.getMessage()));
    }

    @Transactional
    public QuestResponse update(Integer id, UpdateQuestRequest updateQuestRequest){
        Quest quest = questRepository.findById(id)
                .orElseThrow(
                        () -> new QuestNotFoundException(Constant.QUEST_NOT_FIND_IN_DB.getMessage())
                );

        // Why we don't valid on Database ? why we only update instance and by magical this change in DB ?
        quest.applyUpdate(
                updateQuestRequest.title(),
                updateQuestRequest.description(),
                updateQuestRequest.difficulty(),
                updateQuestRequest.requiredLevel(),
                updateQuestRequest.xpReward(),
                updateQuestRequest.goldReward());

        return QuestResponse.from(quest);
    }

    @Transactional
    public void delete(Integer id){
        questRepository.findById(id).orElseThrow(
                () -> new QuestNotFoundException(Constant.QUEST_NOT_FIND_IN_DB.getMessage())
        );
    }

    @Transactional
    public QuestResponse save(CreateQuestRequest createQuestRequest){
        if(questRepository.existsByTitle(createQuestRequest.title())){
            throw new QuestDuplicateTitleException(Constant.QUEST_DUPLICATE_TITLE.getMessage());
        }

        Quest quest = new Quest(
                createQuestRequest.title(),
                createQuestRequest.description(),
                createQuestRequest.difficulty(),
                createQuestRequest.requiredLevel(),
                createQuestRequest.xpReward(),
                createQuestRequest.goldReward()
        );
        quest = questRepository.save(quest);
        return QuestResponse.from(quest);
    }
}
