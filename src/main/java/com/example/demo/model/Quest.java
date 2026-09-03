package com.example.demo.model;

import com.example.demo.model.enums.Difficulty;
import com.example.demo.model.enums.Status;
import jakarta.persistence.*;

@Entity
@Table(name = "quest")
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false, unique = true, length = 100)
    private String title;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = false, length = 20)
    private Difficulty difficulty;

    @Column(name = "required_level", nullable = false, length = 50)
    private int requiredLevel;

    @Column(name = "xp_reward", nullable = false)
    private int xpReward;

    @Column(name = "gold_reward", nullable = false )
    private int goldReward;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private Status status;

    public Quest(String title, String description, Difficulty difficulty, int requiredLevel, int xpReward, int goldReward, Status status){
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.requiredLevel = requiredLevel;
        this.xpReward = xpReward;
        this.goldReward = goldReward;
        this.status = status;
    }

    protected Quest(){}
    //________________________________________________________________________________________
    // ___________________________________  Getter & Setter __________________________________
    public Integer getId(){
        return this.id;
    }

    public String getTitle(){return this.title;}
    // TODO => Protect data before set
    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){return this.description;}
    // TODO => Protect data before set
    public void setDescription(String description){this.description = description;}

    public Difficulty getDifficulty(){return this.difficulty;}
    // TODO => Protect data before set
    public void setDifficulty(Difficulty difficulty){this.difficulty = difficulty;}

    public int getRequiredLevel(){return this.requiredLevel;}
    // TODO => Protect data before set
    public void setRequiredLevel(int requiredLevel){this.requiredLevel = requiredLevel;}

    public int getXpReward(){return this.xpReward;}
    // TODO => Protect data before set
    public void setXpReward(int xpReward){this.xpReward = xpReward;}

    public int getGoldReward(){return this.goldReward;}
    // TODO => Protect data before set
    public void setGoldReward(int goldReward){this.goldReward = goldReward;}

    public Status getStatus() {
        return this.status;
    }
    public void setStatus(Status status){
        this.status = status;
    }
}
