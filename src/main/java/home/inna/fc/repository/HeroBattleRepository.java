package home.inna.fc.repository;

import home.inna.fc.entity.HeroBattleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HeroBattleRepository extends JpaRepository<HeroBattleEntity, HeroBattleEntity.Key> {

    @Query("select battleId from HeroBattleEntity where heroId = ?1")
    Long findByHero(Long heroId);
}
