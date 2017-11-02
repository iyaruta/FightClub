package home.inna.fc.battle;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Battle {

    private Long id;
    private List<Team> teams;
    private LocalDateTime dateTime;
    private int timeout;
}
