package home.inna.fc.battle.handler;

import home.inna.fc.battle.Battle;
import home.inna.fc.battle.Battles;
import home.inna.fc.battle.IHero;
import home.inna.fc.battle.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class FocusHandler {

    @Autowired
    private Battles battles;

    public void handle(Long battleId, Long heroId) {
        Battle battle = battles.getBattle(battleId);
        IHero hero = battle.getHero(heroId);
        List<Team> teams = battle.getOppositeTeams(hero);
        if (teams.isEmpty()) {
            return;
        }

        Random random = new Random();
        int randomTeam = random.nextInt(teams.size());
        Team team = teams.get(randomTeam);
        IHero focus = team.findFocus(hero);
        hero.setFocus(focus);
    }

}
