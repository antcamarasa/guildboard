package com.example.demo.service;

import com.example.demo.dto.adventurer.AdventurerResponse;
import com.example.demo.dto.adventurer.CreateAdventurerRequest;
import com.example.demo.exception.DuplicateNameException;
import com.example.demo.model.Adventurer;
import com.example.demo.repository.AdventurerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdventurerService {
        private final AdventurerRepository adventurerRepository;

        // Pas besoin du auto wired. un seul constructeur => injection de dépendance par default avec annotation spring
        public AdventurerService(AdventurerRepository adventurerRepository){
            this.adventurerRepository = adventurerRepository;
        }

        @Transactional(readOnly = true)
        public List<Adventurer> findAll(){
            return adventurerRepository.findAll();
        }

        @Transactional
        public AdventurerResponse save(CreateAdventurerRequest adventurerRequest){
            // 1. Vérifications du dto

            // 2. Si valide -> 1. Je crée une instance métier(un aventurier) | 2. je le partage a repository pour qu'il le save. | 3. je récupére un nouvelle objet. (ici bizarre quand même en gros j'aimerais bien voir ce qu'il change a part le champs id qui est valide)
            // ensuite, je crée un dto de réponse et je le retourne au controller pour qu'il le return au client.
            //              -> Sinon je crée un erreur et je la retourne.
            if(!adventurerRepository.existsByName(adventurerRequest.getName())){
                Adventurer adventurer = new Adventurer(adventurerRequest.getName(), adventurerRequest.getCharacterType());
                adventurer = adventurerRepository.save(adventurer);
                return new AdventurerResponse(adventurer.getId(), adventurer.getName(), adventurer.getCharacterType(), adventurer.getGold(), adventurer.getXp(), adventurer.getLevel());
            }
            throw  new DuplicateNameException("Name already exists!, try another one.");
        }
    }
