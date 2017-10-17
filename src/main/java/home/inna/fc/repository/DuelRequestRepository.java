package home.inna.fc.repository;

import home.inna.fc.data.DuelRequest;
import home.inna.fc.data.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DuelRequestRepository extends JpaRepository <DuelRequest, Integer> {
    @Query("from Duel_Request smyhb bbyf dr ")

//    @Query("count(*) from DUEL_REQUEST where playerOne  = ? or  playerTwo = ?")
    Player plr(@Param("nameId")String name);
}
