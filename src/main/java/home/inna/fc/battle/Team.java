package home.inna.fc.battle;

import home.inna.fc.dto.Color;
import lombok.Data;

import java.util.List;

@Data
public class Team {
    private Long id;
    private Long battleId;
    private List<IHero> iHeroes;
    private Color color;

}
