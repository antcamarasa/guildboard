package com.example.demo.model;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    // LONG ?
    private Integer id;

    @JoinColumn(name = "adventurer_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Adventurer adventurer;

    @JoinColumn(name = "quest_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Quest quest;

    @Column(name = "assigned_at", nullable = false)
    private OffsetDateTime assignedAt;

    @Column(name = "completed_at")
    private OffsetDateTime completedAt;

    // No arg constructor for hibernate
    protected Assignment(){}

    public Assignment(Adventurer adventurer, Quest quest){
        this.adventurer = adventurer;
        this.quest = quest;
        this.assignedAt = OffsetDateTime.now();
    }

    //________________________________________________________________________________________
    // ___________________________________  Getter & Setter __________________________________
    public Integer getId(){
        return this.id;
    }

    public Adventurer getAdventurer(){
        return this.adventurer;
    }
    // No sense to change adventurer from an assignment, object is independant
    //public void setAdventurer(Adventurer adventurer){this.adventurer = adventurer;}

    public Quest getQuest(){return this.quest;}
    // No sense, idem of adventurer
    //public void setQuest(Quest quest){this.quest = quest;}

    public OffsetDateTime getAssignedAt(){
        return this.assignedAt;
    }
    // On ne set pas assigned At car, il est assigné dans sa création.

    public OffsetDateTime getCompletedAt(){
            return this.completedAt;
    }
    public void setCompletedAt(OffsetDateTime completedAt){
        this.completedAt = completedAt;
    }

}
