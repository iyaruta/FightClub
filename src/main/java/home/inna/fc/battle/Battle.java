package home.inna.fc.battle;

import home.inna.fc.dto.Color;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class Battle {

    private Long id;
    private int timeout;
    private LocalDateTime dateTime;
    private Map<Color, Team> teams = new HashMap<>();


}
