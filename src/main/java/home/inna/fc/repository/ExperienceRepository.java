package home.inna.fc.repository;

import home.inna.fc.data.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Integer> {

    @Query("from Experience where experience > ? and experience <= ?")
    List<Experience> findByRange(int currentExp, int newExp);



}


