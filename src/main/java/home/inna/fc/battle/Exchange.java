package home.inna.fc.battle;

import org.springframework.stereotype.Component;

@Component
public class Exchange {

    public void exchange(IHero hero, Attack attack) {
        IHero enemy = hero.getFocus();
        Attack enemyAttack = hero.findEnemyAttack(enemy.getId());
        for (Zone atck : attack.getAttacks()) {
            if (enemyAttack.getBlock().hasBlocked(atck, 2)) {
                enemy.setCurrentHealth(-1);
            }
            enemy.setCurrentHealth(0);
        }
    }

}
