package com.example.demo.service;

import com.example.demo.constant.Constant;
import com.example.demo.dto.assignment.response.AssignmentResponse;
import com.example.demo.exception.adventurer.AdventurerNotFoundException;
import com.example.demo.exception.assignment.*;
import com.example.demo.exception.quest.QuestNotFoundException;
import com.example.demo.model.Adventurer;
import com.example.demo.model.Assignment;
import com.example.demo.model.Quest;
import com.example.demo.repository.AdventurerRepository;
import com.example.demo.repository.AssignmentRepository;
import com.example.demo.repository.QuestRepository;
import com.example.demo.util.RepositoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AssignmentService {
    private final AdventurerRepository adventurerRepository;
    private final QuestRepository questRepository;
    private final AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentService(AdventurerRepository adventurerRepository, QuestRepository questRepository, AssignmentRepository assignmentRepository) {
        this.adventurerRepository = adventurerRepository;
        this.questRepository = questRepository;
        this.assignmentRepository = assignmentRepository;
    }

    @Transactional
    public List<AssignmentResponse> findAll(){

        List<Assignment> listAssignmentInDatabase = assignmentRepository.findAll();

        List<AssignmentResponse> result = new ArrayList<>();

        for(Assignment assignment : listAssignmentInDatabase){
            result.add(AssignmentResponse.from(assignment));
        }

        return result;
    }

    @Transactional
    public AssignmentResponse findAllById(Integer assignmentId){
        Assignment assignment = RepositoryUtil.getOrThrow(assignmentRepository, assignmentId, () -> new AssignmentNotFind(Constant.ASSIGNMENT_NOT_FIND.getMessage()));
        return AssignmentResponse.from(assignment);
    }

    @Transactional
    public List<AssignmentResponse> findByAdventurerId(Integer adventurerId){
        List<Assignment> assignments = assignmentRepository.findByAdventurerId(adventurerId);
        if(assignments.isEmpty()){
            throw new AssignmentNotFind(Constant.ASSIGNMENT_NOT_FIND.getMessage());
        }

        List<AssignmentResponse> assignmentResponses = new ArrayList<>();
        for(Assignment assignment : assignments){
            assignmentResponses.add(AssignmentResponse.from(assignment));
        }

        return assignmentResponses;
    }

    @Transactional
    public AssignmentResponse save(Integer adventurerId, Integer questId){
        Adventurer adventurer = RepositoryUtil.getOrThrow(adventurerRepository, adventurerId, () -> new AdventurerNotFoundException(Constant.ADVENTURER_NOT_FIND_IN_DB.getMessage()));
        Quest quest = RepositoryUtil.getOrThrow(questRepository, questId, () -> new QuestNotFoundException(Constant.QUEST_NOT_FIND_IN_DB.getMessage()));

        requireAdventurerIsAvailable(adventurerId); // Pas de quêtes en cours et a le niveau
        requireQuestNotAlreadyDone(adventurerId, questId);//

        quest.assignTo(adventurer.getLevel());


        Assignment assignment = new Assignment(adventurer, quest);
        assignmentRepository.save(assignment);

        return AssignmentResponse.from(assignment);
    }

    @Transactional
    public AssignmentResponse complete(Integer assignmentId){
        Assignment assignment = RepositoryUtil.getOrThrow(assignmentRepository, assignmentId, () -> new AssignmentNotFind(Constant.ASSIGNMENT_NOT_FIND.getMessage()));
        assignment.setCompletedAt(OffsetDateTime.now());
        assignment = assignmentRepository.save(assignment);
        assignment.getQuest().updateStatus();

        return AssignmentResponse.from(assignment);
    }


    // ********************************************************************************
    // _________________________________ Helper methode _______________________________
    private void requireAdventurerIsAvailable(Integer adventurerId){
        if(!assignmentRepository.findByAdventurerId(adventurerId).stream().allMatch(Assignment::isCompleted)) {
            throw new AdventurerHaveAssignmentInProgress(Constant.ADVENTURER_HAVE_ASSIGNMENT_IN_PROGRESS.getMessage());
        }
    }
    private void requireQuestNotAlreadyDone(Integer adventurerId, Integer questId){
        if(assignmentRepository.findByAdventurerIdAndQuestId(adventurerId, questId).isPresent()){
            throw new AdventurerHaveAlreadyDoneThisQuest(Constant.ADVENTURER_ALREADY_DONE_THIS_QUEST.getMessage());
        };
    }
}
