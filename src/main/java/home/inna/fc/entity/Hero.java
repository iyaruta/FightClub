package home.inna.fc.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "hero")
@Data
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id")
    private Long accountId;
    private String name;
    private int ability;
    private int force;
    private int agility;
    private int instinct;
    private int stamina;
    private int experience;
    private int level;
    private int health;

}
