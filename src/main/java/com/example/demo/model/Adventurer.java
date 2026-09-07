package com.example.demo.model;
import com.example.demo.model.enums.AdventurerType;
import jakarta.persistence.*;

@Entity
@Table(name = "adventurer")
public class Adventurer {
    @Id
    // Quelle est la dif entre IDENTITY et AUTO ?
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "adventurer_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdventurerType characterType;

    @Column(name = "level", nullable = false)
    private int level;

    @Column(name = "xp", nullable = false)
    private int xp;

    @Column(name = "gold", nullable = false)
    private int gold;

    // No arg protected constructor for hibernate : introspection
    // don't protect from jackson who serialize and deserialize json.
    protected Adventurer(){}

    public Adventurer(String name, AdventurerType characterType){
        this.name = name;
        this.characterType = characterType;
        this.level = 1;
        this.xp = 0;
        this.gold = 0;
    }


    //________________________________________________________________________________________
    // ___________________________________  Getter & Setter __________________________________
    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getLevel(){return this.level;}
    public void setLevel(int level){this.level = level;}

    public int getXp(){return this.xp;}
    public void setXp(int xp){this.xp = xp;}

    public int getGold(){return this.gold;}
    public void setGold(int gold){this.gold = gold;}


    public AdventurerType getCharacterType(){
        return this.characterType;
    }
}
