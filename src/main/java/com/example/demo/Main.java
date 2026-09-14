package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO : Restant a faire pour le back-end
 * 1. AssignmentService complete
 * 2. Règles metier xp, gold, level
 * 3. Règles métier on ne peut pas supprimer un personnage si il est en train de faire une quête
 * 4. Idem pour la quête on ne peut pas la supprimer si elle est en train d'être faite.
 * Et voila.
 */

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
