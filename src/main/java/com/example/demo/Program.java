package com.example.demo;
import com.example.demo.model.Adventurer;
import com.example.demo.model.enums.AdventurerType;

import java.sql.Connection;
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
        AdventurerType adventurerType;

        while (true){
            System.out.println("Choose a name > ");
            name = scanner.nextLine();
            if (!isValidName(name))continue;

            System.out.println("Choose a character type beetween > ");
            for (AdventurerType type : AdventurerType.values()){
                System.out.println(type.getType());
            }

            String characterTypeStr = scanner.nextLine().trim().toUpperCase();
            adventurerType = switch (characterTypeStr){
                case "WARRIOR" :
                    yield AdventurerType.WARRIOR;
                case "MAGE" :
                    yield AdventurerType.MAGE;
                case "RANGER" :
                    yield AdventurerType.RANGER;
                case "CLERIC" :
                    yield AdventurerType.CLERIC;
                default:
                    yield null;
            };

            if(!isValidCharacterType(adventurerType))continue;
            break;
        }
        // Je crée l'objet en BDD comment ?
        return new Adventurer(name, adventurerType);
    }

    //TODO
    private boolean isValidName(String name){return true;}
    private boolean isValidCharacterType(AdventurerType adventurerType){return true;}
}
