package home.inna.fc.repository;

import home.inna.fc.data.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Player findByName(String name);

//    @Query("update Plaeyr set  name = player.getName, force = : force, agility = agility, + " +
//            "insinct = :insinct, stamina = :stamina, level = :level,+" +
//            "health = :health, experience = :experience where firstName = :name")
//    Player saveOrUpdate(Player player);



}
