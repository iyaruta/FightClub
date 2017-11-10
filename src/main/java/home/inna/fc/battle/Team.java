package home.inna.fc.battle;

import home.inna.fc.dto.Color;
import lombok.Data;

import java.util.List;

@Data
public class Team {
    private Color color;
    private Long battleId;
    private List<IHero> heroes;

}
