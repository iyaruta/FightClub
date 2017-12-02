package home.inna.fc.battle;

import home.inna.fc.dto.Color;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class Battle {

    private Long id;
    private int timeout;
    private LocalDateTime dateTime;
    private Map<Color, Team> teams = new LinkedHashMap<>();

    public IHero getHero(Long heroId) {
        return teams.values().stream()
                .map(team -> team.getHero(heroId))
                .filter(Objects::nonNull)
                .findFirst().orElse(null);
    }

    public List<Team> getOppositeTeams(IHero hero) {
        return teams.entrySet().stream()
                .filter(e -> e.getKey() != hero.getColor())
                .filter(e -> e.getValue().hasAlive())
                .map(e -> e.getValue())
                .collect(Collectors.toList());
    }
}
