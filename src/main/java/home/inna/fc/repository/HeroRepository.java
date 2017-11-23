package home.inna.fc.repository;

import home.inna.fc.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HeroRepository extends JpaRepository<Hero, Long> {

    @Query("from Hero p where lower(p.name) = lower(:name)")
    Hero findByName(@Param("name") String name);

    Hero findByAccountId(Long accountId);


}
