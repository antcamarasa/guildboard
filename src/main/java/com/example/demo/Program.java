package com.example.demo;

import com.example.demo.Character.Adventurer;
import com.example.demo.Character.CharacterType;

import java.util.Scanner;

public class Program {
    Scanner scanner = new Scanner(System.in);

    public void start(){
        System.out.println("Welcome in GuildBoard");

        String playerChoice;
        Adventurer adventurer;

        while (true){
            System.out.println("Choose action : ");
            System.out.println("1. Create adventurer");
            System.out.println("2. List all adventurer");
            playerChoice = scanner.nextLine();

            if(playerChoice.equals("1")){
                adventurer = createAdventurer();
                System.out.println("You create a adenturer : " + adventurer.getName() + " Type : " + adventurer.getCharacterType());
                break;
            } else if(playerChoice.equals("2")){
                return;
            }
        }
    }

    private Adventurer createAdventurer(){
        String name;
        CharacterType characterType;

        while (true){
            System.out.println("Choose a name > ");
            name = scanner.nextLine();
            if (!isValidName(name))continue;

            System.out.println("Choose a character type beetween > ");
            for (CharacterType type : CharacterType.values()){
                System.out.println(type.getType());
            }

            String characterTypeStr = scanner.nextLine().trim().toUpperCase();
            characterType = switch (characterTypeStr){
                case "WARRIOR" :
                    yield CharacterType.WARRIOR;
                case "MAGE" :
                    yield CharacterType.MAGE;
                case "RANGER" :
                    yield CharacterType.RANGER;
                case "CLERIC" :
                    yield CharacterType.CLERIC;
                default:
                    yield null;
            };

            if(!isValidCharacterType(characterType))continue;
            break;
        }
        // Je crée l'objet en BDD comment ?
        return new Adventurer(name, characterType);
    }

    //TODO
    private boolean isValidName(String name){return true;}
    private boolean isValidCharacterType(CharacterType characterType){return true;}
}
