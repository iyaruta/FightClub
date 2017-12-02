package home.inna.fc.battle;

import home.inna.fc.dto.Color;
import lombok.Data;

import java.util.List;
import java.util.Random;

@Data
public class Team {
    private Color color;
    private Long battleId;
    private List<IHero> heroes;
    private List<IHero> dead;

    public boolean hasAlive() {
        return heroes.size() > 0;
    }

    public IHero getHero(Long heroId) {
        return heroes.stream().filter(h -> heroId.equals(h.getId())).findFirst().orElse(null);
    }

    public IHero findFocus(IHero hero) {
        Random random = new Random();
        int randomHero = random.nextInt(heroes.size());
        return heroes.get(randomHero);
    }

}
