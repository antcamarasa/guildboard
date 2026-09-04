package com.example.demo.config;

import com.example.demo.model.Adventurer;
import com.example.demo.model.Assignment;
import com.example.demo.model.Quest;
import com.example.demo.model.enums.AdventurerType;
import com.example.demo.model.enums.Difficulty;
import com.example.demo.model.enums.Status;
import com.example.demo.repository.AdventurerRepository;
import com.example.demo.repository.AssignmentRepository;
import com.example.demo.repository.QuestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataSeeder implements CommandLineRunner {
    private final QuestRepository questRepository;
    private final AdventurerRepository adventurerRepository;
    private final AssignmentRepository assignmentRepository;

    public DataSeeder(QuestRepository questRepository, AdventurerRepository adventurerRepository, AssignmentRepository assignmentRepository){
        this.questRepository = questRepository;
        this.adventurerRepository = adventurerRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public Map<String, Adventurer> createAdventurer(){
        Map<String, Adventurer> adventurerMap = new HashMap<>();

        Adventurer brannoc = new Adventurer("Brannoc Ironhelm", AdventurerType.WARRIOR);
        brannoc.setLevel(3);
        brannoc.setXp(120);
        brannoc.setGold(450);
        adventurerMap.put("Brannoc", brannoc);

        Adventurer sylwen = new Adventurer("Sylwen Ashgrove", AdventurerType.MAGE);
        sylwen.setLevel(2);
        sylwen.setXp(60);
        sylwen.setGold(180);
        adventurerMap.put("Sylwen", sylwen);

        Adventurer doran = new Adventurer("Doran Blackfletch", AdventurerType.RANGER);
        doran.setLevel(1);
        doran.setXp(40);
        doran.setGold(75);
        adventurerMap.put("Doran", doran);

        return adventurerMap;
    }
    public Map<String, Quest> createQuest(){
        Map<String, Quest> questMap = new HashMap<>();

        // Rats in the Cellar	The innkeeper swears they are the size of dogs. They are not, but they do bite.	EASY	1	20	50	COMPLETED
        Quest ratsInTheCellar = new Quest("Rats in the Cellar", "The innkeeper swears they are the size of dogs. They are not, but they do bite.", Difficulty.EASY, 1, 50, 20);
        ratsInTheCellar.setStatus(Status.COMPLETED);
        questMap.put("ratsInTheCellar", ratsInTheCellar);

        Quest theMissingGoat = new Quest("The Missing Goat", "Old Merrick lost his prize goat near the treeline. He is offering more than it is worth.", Difficulty.EASY, 1, 40, 15);
        theMissingGoat.setStatus(Status.COMPLETED);
        questMap.put("theMissingGoat", theMissingGoat);

        Quest herbsForTheHealer = new Quest("Herbs for the Healer", "Gather bloodroot from the marsh. Watch your footing, the ground lies.", Difficulty.EASY, 1, 60, 25);
        herbsForTheHealer.setStatus(Status.COMPLETED);
        questMap.put("herbsForTheHealer", herbsForTheHealer);

        Quest theBrokenMillstone = new Quest("The Broken Millstone", "Bandits smashed the mill and took the miller's daughter's dowry. Get it back.", Difficulty.MEDIUM, 2, 120, 80);
        theBrokenMillstone.setStatus(Status.COMPLETED);
        questMap.put("theBrokenMillstone", theBrokenMillstone);

        Quest smokeOverGreyFen = new Quest("Smoke over Greyfen", "A farmstead burned in the night and nobody will say why. Find out.", Difficulty.MEDIUM, 2, 140, 90);
        smokeOverGreyFen.setStatus(Status.COMPLETED);
        questMap.put("smokeOverGreyFen", smokeOverGreyFen);

        Quest wolvesAtGarrowmere = new Quest("Wolves at Harrowmere", "The pack has grown bold enough to take a shepherd. They will take another.", Difficulty.MEDIUM, 3, 160, 100);
        wolvesAtGarrowmere.setStatus(Status.COMPLETED);
        questMap.put("wolvesAtGarrowmere", wolvesAtGarrowmere);

        Quest theTolKeepersDebt = new Quest("The Tollkeeper's Debt", "He has not paid the guild in three seasons. Collect, politely if possible.", Difficulty.MEDIUM, 2, 110, 75);
        theTolKeepersDebt.setStatus(Status.COMPLETED);
        questMap.put("theTolKeepersDebt", theTolKeepersDebt);

        Quest theSunkenChapel = new Quest("The Sunken Chapel", "Something in the flooded crypt is keeping the water from draining.", Difficulty.HARD, 3, 280, 200);
        theSunkenChapel.setStatus(Status.AVAILABLE);
        questMap.put("theSunkenChapel", theSunkenChapel);

        Quest ledgerOfTheDrownedMan = new Quest("Ledger of the Drowned Man", "A merchant washed ashore with a locked ledger. His partners want it burned.", Difficulty.HARD, 4, 320, 250);
        ledgerOfTheDrownedMan.setStatus(Status.AVAILABLE);
        questMap.put("ledgerOfTheDrownedMan", ledgerOfTheDrownedMan);

        Quest theHollowCrown = new Quest("The Hollow Crown", "The old king's barrow has been opened from the inside.", Difficulty.EPIC, 5, 800, 600);
        theHollowCrown.setStatus(Status.AVAILABLE);
        questMap.put("theHollowCrown", theHollowCrown);

        return questMap;
    }
    public Map<String, Assignment> createAssignment(Map<String, Adventurer> adventurerMap, Map<String, Quest> questMap){
        Map<String, Assignment> assignmentMap = new HashMap<>();

        Assignment assignment_1 = new Assignment(adventurerMap.get("Brannoc"), questMap.get("ratsInTheCellar"));
        assignment_1.setCompletedAt(OffsetDateTime.now());
        assignmentMap.put("assignment_1", assignment_1);

        Assignment assignment_2 = new Assignment(adventurerMap.get("Brannoc"), questMap.get("theBrokenMillstone"));
        assignment_2.setCompletedAt(OffsetDateTime.now());
        assignmentMap.put("assignment_2", assignment_2);

        Assignment assignment_3 = new Assignment(adventurerMap.get("Brannoc"), questMap.get("wolvesAtGarrowmere"));
        assignment_3.setCompletedAt(OffsetDateTime.now());
        assignmentMap.put("assignment_3", assignment_3);

        Assignment assignment_4 = new Assignment(adventurerMap.get("Sylwen"), questMap.get("herbsForTheHealer"));
        assignment_4.setCompletedAt(OffsetDateTime.now());
        assignmentMap.put("assignment_4", assignment_4);

        Assignment assignment_5 = new Assignment(adventurerMap.get("Sylwen"), questMap.get("smokeOverGreyFen"));
        assignment_5.setCompletedAt(OffsetDateTime.now());
        assignmentMap.put("assignment_5", assignment_5);

        Assignment assignment_6 = new Assignment(adventurerMap.get("Doran"), questMap.get("theMissingGoat"));
        assignment_6.setCompletedAt(OffsetDateTime.now());
        assignmentMap.put("assignment_6", assignment_6);

        return assignmentMap;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        assignmentRepository.deleteAll();
        adventurerRepository.deleteAll();
        questRepository.deleteAll();

        Map<String, Adventurer> adventurerMap = createAdventurer();
        Map<String, Quest> questMap = createQuest();
        Map<String, Assignment> assignmentMap = createAssignment(adventurerMap, questMap);

        for(Map.Entry<String, Adventurer> adventurerEntry : adventurerMap.entrySet()){
            adventurerRepository.save(adventurerEntry.getValue());
        }

        for(Map.Entry<String, Quest> questEntry : questMap.entrySet()){
            questRepository.save(questEntry.getValue());
        }

        for(Map.Entry<String, Assignment> assignmentEntry : assignmentMap.entrySet()){
            assignmentRepository.save(assignmentEntry.getValue());
        }
    }
}
