package home.inna.fc.battle;

import home.inna.fc.dto.Color;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class IHero {

    private Long id;
    private Long battleId;
    private Color color;
    private String name;
    private int ability;
    private int force;
    private int agility;
    private int instinct;
    private int stamina;
    private int level;
    private int health;
    private int currentHealth;

    private IHero focus;
    private Map<Long, Attack> selfAttacks = new HashMap<>();
    private Map<Long, Attack> enemyAttacks = new HashMap<>();
    private List<Effects> effects = new ArrayList<>();


    public void attack(Attack attack) {
        focus.enemyAttacks.put(id, attack);
        selfAttacks.put(focus.getId(), attack);
        focus = null;
    }

    public Attack findEnemyAttack(Long heroId) {
        return enemyAttacks.get(heroId);
    }

    public void removeAttack() {
        focus.selfAttacks.remove(id);
        enemyAttacks.remove(focus.getId());
    }
}
