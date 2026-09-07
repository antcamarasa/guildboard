package com.example.demo.dto.adventurer;

import com.example.demo.model.enums.AdventurerType;

// Permet de créer un entités précise sécurité des champs authorisé
// En gros on reprend le controle, sur ce que l'utilisateur nous donne.
public class CreateAdventurerRequest {
    private final String name;
    private final AdventurerType characterType;

    // il faut être fidèle au json.
    public CreateAdventurerRequest(String name, AdventurerType characterType){
        this.name = name;
        this.characterType = characterType;
    }

    // Getter
    public String getName(){
        return this.name;
    }

    public AdventurerType getCharacterType(){
        return this.characterType;
    }
}
