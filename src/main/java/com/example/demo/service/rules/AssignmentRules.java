package com.example.demo.service.rules;

import com.example.demo.model.Assignment;
import com.example.demo.model.enums.Status;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AssignmentRules {
    public static boolean checkAdventurerLevel(int adventurerLevel, int questRequiredLevel){
        return adventurerLevel >= questRequiredLevel;
    }
    public static boolean checkQuestIsAvailable(Status status){
        return status == Status.AVAILABLE;
    }
    public static boolean checkAdventurerHasAssignmentInProgress(Optional<Assignment> assignment){
        return assignment.isPresent();
    }
    public static boolean checkAdventurerHaventDoneThisQuestYet(List<Assignment> assignments, Integer questId){
        if(assignments.isEmpty())return false;
       // Dans l'assignment correspondant a l'aventurier, si la quête présent dans l'assignment est la le meme que la quête demandé
       // alors, pas possible. comme juste avant on a vérifier si une quête était en cours -, l'ordre déduit qu'il s'agit d'une quête completed
       // Pour plus de sécurité => je pourrai vérifier si assignment.completed_at != null; mais overkill.
        for(Assignment assignment : assignments){

            if(Objects.equals(assignment.getQuest().getId(), questId)){
                return false;
            }
        }
        return true;
    }
}
