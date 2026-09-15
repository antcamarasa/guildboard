package com.example.demo.repository.specification;

import com.example.demo.model.Quest;
import com.example.demo.model.enums.Difficulty;
import com.example.demo.model.enums.Status;
import org.springframework.data.jpa.domain.Specification;

public class QuestSpecification {

    public static Specification<Quest> hasStatus(Status status){
        // Comme le type de retour est specification<Quest> alors il sait que si j'écris un lambda il va regarder dans la classe specification<Quest> si il existe une lambda ?
        return (root, query, builder)
                -> status == null ? null : builder.equal(root.get("status"), status);
    }

    public static Specification<Quest> hasDifficulty(Difficulty difficulty){
        return (root, query, builder)
                -> difficulty == null ? null : builder.equal(root.get("difficulty"), difficulty);
    }
}
