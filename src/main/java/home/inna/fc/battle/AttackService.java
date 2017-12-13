package home.inna.fc.battle;

import home.inna.fc.battle.handler.FocusHandler;
import home.inna.fc.repository.HeroBattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttackService {

    @Autowired
    private HeroBattleRepository heroBattle;

    @Autowired
    private Battles battles;

    @Autowired
    private FocusHandler focusHandler;

    @Autowired
    private Exchange exchange;

    public void attack(Long heroId, Attack attack) {
        Long battleId = heroBattle.findByHero(heroId);
        Battle battle = battles.getBattle(battleId);
        IHero hero = battle.getHero(heroId);

        IHero focus = hero.getFocus();

        Attack enemyAttack = hero.findEnemyAttack(focus.getId());
        if (enemyAttack == null) {
            hero.attack(attack);
        } else {
            exchange.exchange(hero, attack);
            hero.removeAttack();
        }

        focusHandler.handle(battleId, heroId);
        focusHandler.handle(battleId, focus.getId());
    }
}
