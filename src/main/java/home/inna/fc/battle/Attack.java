package home.inna.fc.battle;

import lombok.Data;

import java.util.List;

@Data
public class Attack {

    private List<Zone> attacks;
    private Zone block;
}
