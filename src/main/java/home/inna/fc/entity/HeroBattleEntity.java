package home.inna.fc.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hero_in_battle")
@Data
@IdClass(HeroBattleEntity.Key.class)
public class HeroBattleEntity implements Serializable {

    @Id
    @Column(name = "battle_id")
    private Long battleId;

    @Id
    @Column(name = "hero_id")
    private Long heroId;


    @Data
    public static class Key implements Serializable {

        private Long battleId;
        private Long heroId;

    }

}
