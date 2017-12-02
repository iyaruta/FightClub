package home.inna.fc.battle;

import home.inna.fc.dto.Color;
import home.inna.fc.entity.BattleEntity;
import home.inna.fc.entity.DuelRequest;
import home.inna.fc.entity.Hero;
import home.inna.fc.repository.BattleRepository;
import home.inna.fc.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class BattleBuilder {

    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private Battles battles;

    public Battle build(DuelRequest request) {
        Battle battle = create(request);

        IHero iHeroOne = iHero(battle.getId(), request.getHeroOne(), Color.RED);
        IHero iHeroTwo = iHero(battle.getId(), request.getHeroTwo(), Color.BLUE);

        Team team1 = team(iHeroOne);
        Team team2 = team(iHeroTwo);

        battle.getTeams().put(Color.RED, team1);
        battle.getTeams().put(Color.BLUE, team2);

        battles.add(battle);

        return battle;
    }


    private Team team(IHero hero) {
        Team team = new Team();
        team.setBattleId(hero.getBattleId());
        team.setColor(hero.getColor());

        List<IHero> heroes = new ArrayList<>();
        heroes.add(hero);
        team.setHeroes(heroes);

        return team;
    }

    private Battle create(DuelRequest request) {
        BattleEntity battleEntity = new BattleEntity();
        battleEntity.setDateTime(LocalDateTime.now());
        battleEntity.setTimeout(request.getTimeout());
        battleRepository.save(battleEntity);
        Battle battle = new Battle();
        battle.setId(battleEntity.getId());
        battle.setDateTime(battleEntity.getDateTime());
        battle.setTimeout(battleEntity.getTimeout());

        return battle;

    }

    private IHero iHero(Long battleId, Long heroId, Color color) {
        Hero hero = heroRepository.findOne(heroId);
        IHero iHero = new IHero();
        iHero.setBattleId(battleId);
        iHero.setColor(color);
        iHero.setId(heroId);
        iHero.setName(hero.getName());
        iHero.setAbility(hero.getAbility());
        iHero.setForce(hero.getForce());
        iHero.setAgility(hero.getAgility());
        iHero.setInstinct(hero.getInstinct());
        iHero.setStamina(hero.getStamina());
        iHero.setLevel(hero.getLevel());
        iHero.setHealth(hero.getHealth());
        iHero.setCurrentHealth(hero.getHealth());

        return iHero;
    }

}
