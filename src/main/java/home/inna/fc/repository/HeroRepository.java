package home.inna.fc.repository;

import home.inna.fc.data.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HeroRepository extends JpaRepository<Hero, Integer> {

    @Query("from Hero p where lower(p.name) = lower(:name)")
    Hero findByName(@Param("name") String name);

//    @Query("update Plaeyr set  name = hero.getName, force = : force, agility = agility, + " +
//            "insinct = :insinct, stamina = :stamina, level = :level,+" +
//            "health = :health, experience = :experience where firstName = :name")
//    Hero saveOrUpdate(Hero hero);



}
