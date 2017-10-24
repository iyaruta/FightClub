package home.inna.fc.repository;

import home.inna.fc.data.DuelRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DuelRequestRepository extends JpaRepository <DuelRequest, Long> {

//    @Query("count(*) from DUEL_REQUEST where playerOne  = ? or  playerTwo = ?")


}
