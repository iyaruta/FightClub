package home.inna.fc.battle;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Battles {
    private final Map<Long, Battle> battles = new HashMap<>();

    public void add(Battle battle) {
        battles.put(battle.getId(), battle);
    }

    public Battle getBattle(Long battleId) {
        return battles.get(battleId);
    }

}
