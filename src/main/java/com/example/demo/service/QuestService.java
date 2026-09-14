package com.example.demo.service;

import com.example.demo.constant.Constant;
import com.example.demo.dto.quest.request.CreateQuestRequest;
import com.example.demo.dto.quest.request.UpdateQuestRequest;
import com.example.demo.dto.quest.response.QuestResponse;
import com.example.demo.exception.quest.NoQuestInDataBase;
import com.example.demo.exception.quest.QuestDuplicateTitleException;
import com.example.demo.exception.quest.QuestNotFoundException;
import com.example.demo.model.Quest;
import com.example.demo.model.enums.Difficulty;
import com.example.demo.model.enums.Status;
import com.example.demo.repository.QuestRepository;
import com.example.demo.util.RepositoryUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestService {
    // Fields
    private final QuestRepository questRepository;

    // Constructor
    public QuestService(QuestRepository questRepository){
        this.questRepository = questRepository;
    }



    @Transactional(readOnly = true)
    public List<QuestResponse> findAll(Status status, Difficulty difficulty) {
        List<Quest> quests;
        if(status == null && difficulty == null){
            quests = questRepository.findAll();
        } else if(status != null && difficulty == null) {
            quests = questRepository.filterByStatus(status);
        } else if (status == null && difficulty != null) {
            quests = questRepository.filterByDifficulty(difficulty);
        } else{
            quests = questRepository.filterByStatusAndDifficulty(status, difficulty);
        }

        List<QuestResponse> questResponses = new ArrayList<>();
        for(Quest quest : quests){
            questResponses.add(QuestResponse.from(quest));
        }
        return questResponses;
    }

    @Transactional(readOnly = true)
    public Quest findById(Integer id){
        return RepositoryUtil.getOrThrow(
                questRepository,
                id,
                () -> new QuestNotFoundException(Constant.QUEST_NOT_FIND_IN_DB.getMessage()
                )
        );
    }

    @Transactional
    public QuestResponse update(Integer id, UpdateQuestRequest updateQuestRequest){
        Quest quest = RepositoryUtil.getOrThrow(
                questRepository,
                id,
                () -> new QuestNotFoundException(Constant.QUEST_NOT_FIND_IN_DB.getMessage()
                )
        );

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
