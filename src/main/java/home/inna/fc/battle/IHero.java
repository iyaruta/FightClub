package home.inna.fc.battle;

import home.inna.fc.dto.Color;
import lombok.Data;

import java.util.List;

@Data
public class IHero {

    private Long id;
    private Long battleId;
    private Color color;
    private List<Effects> effects;
    private int focus;
    private String name;
    private int ability;
    private int force;
    private int agility;
    private int instinct;
    private int stamina;
    private int level;
    private int health;
    private int currentHealth;

}
