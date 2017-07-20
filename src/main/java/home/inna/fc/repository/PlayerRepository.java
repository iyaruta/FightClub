package home.inna.fc.repository;

import home.inna.fc.data.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByName(String name);

}
