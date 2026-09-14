package com.example.demo.util;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.Supplier;

public class RepositoryUtil {
    /**
     * This method able us to get the entity from id search entity on db, or throw runtime exception
     * @param repository - the repository that extends JpaRepository
     * @param id - id of the entity search
     * @param error - Class that extends runTimeException
     * @return - the entity searched or error to throw
     * @param <L>
     * @param <T>
     */
    public static <L, T extends JpaRepository<L, Integer>> L getOrThrow(T repository, Integer id, Supplier<? extends RuntimeException> error){
        return repository.findById(id).orElseThrow(error);
    }

    /**
     *
     * @param repository
     * @param <T> : instance of model storage in U repository
     * @param <I> : Type of index in repo
     * @param <U> : repositoryInstance
     */
    public static <T, I, U extends JpaRepository<T, I>> T save(U repository, T t){
        return repository.save(t);
    }
}
