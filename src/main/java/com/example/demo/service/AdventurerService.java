package com.example.demo.service;

import com.example.demo.constant.Constant;
import com.example.demo.dto.adventurer.AdventurerResponse;
import com.example.demo.dto.adventurer.CreateAdventurerRequest;
import com.example.demo.dto.adventurer.UpdateAdventurerRequest;
import com.example.demo.exception.Adventurer.AdventurerNotFoundException;
import com.example.demo.exception.Adventurer.DuplicateNameException;
import com.example.demo.model.Adventurer;
import com.example.demo.repository.AdventurerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//
@Service
public class AdventurerService {
        private final AdventurerRepository adventurerRepository;

        @Autowired
        public AdventurerService(AdventurerRepository adventurerRepository){
            this.adventurerRepository = adventurerRepository;
        }

        @Transactional(readOnly = true)
        public AdventurerResponse findById(Integer id){
            Adventurer adventurer = adventurerRepository
                    .findById(id)
                    .orElseThrow(() -> new AdventurerNotFoundException(Constant.ADVENTURER_NOT_FIND_IN_DB.getMessage()));

            return AdventurerResponse.from(adventurer);
        }

        @Transactional(readOnly = true)
        public List<AdventurerResponse> findAll(){
            var adventurers = adventurerRepository.findAll();

            List<AdventurerResponse> adventurerResponseList = new ArrayList<>();
            for(Adventurer adventurer : adventurers){
                adventurerResponseList.add(AdventurerResponse.from(adventurer));
            }
            return adventurerResponseList;
        }

        @Transactional
        public AdventurerResponse save(CreateAdventurerRequest adventurerRequest){
            if(!adventurerRepository.existsByName(adventurerRequest.getName())){
                Adventurer adventurer = new Adventurer(adventurerRequest.getName(), adventurerRequest.getCharacterType());
                adventurer = adventurerRepository.save(adventurer);
                return AdventurerResponse.from(adventurer);
            }
            throw  new DuplicateNameException(Constant.NAME_ALREADY_EXIST_IN_DB.getMessage());
        }

        @Transactional
        public void delete(Integer id){
            // TODO : A vérifier => On ne pourra supprimer un aventurier qui a un Assignment en cours.
            Adventurer adventurer = findAventurer(id);
            adventurerRepository.delete(adventurer);
        }

        @Transactional
        public AdventurerResponse update(Integer id, UpdateAdventurerRequest updateAdventurerRequest){
            Adventurer adventurer = adventurerRepository.findById(id).orElseThrow(
                    () -> new AdventurerNotFoundException(Constant.ADVENTURER_NOT_FIND_IN_DB.getMessage())
            );
            adventurer.applyUpdate(updateAdventurerRequest.name(), updateAdventurerRequest.adventurerType(), updateAdventurerRequest.level(), updateAdventurerRequest.xp(), updateAdventurerRequest.gold());
            return AdventurerResponse.from(adventurer);
        }


        // _____________________ Helper Method _____________________
        public Adventurer findAventurer(Integer id){
            return adventurerRepository.findById(id).orElseThrow(
                    () -> new AdventurerNotFoundException(Constant.ADVENTURER_NOT_FIND_IN_DB.getMessage())
            );
        }
    }
