package home.inna.fc.repository;

import home.inna.fc.data.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query("from Player p where lower(p.name) = lower(:name)")
    Player findByName(@Param("name") String name);

//    @Query("update Plaeyr set  name = player.getName, force = : force, agility = agility, + " +
//            "insinct = :insinct, stamina = :stamina, level = :level,+" +
//            "health = :health, experience = :experience where firstName = :name")
//    Player saveOrUpdate(Player player);



}
