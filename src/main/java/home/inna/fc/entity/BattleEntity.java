package home.inna.fc.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "battle")
@Data
public class BattleEntity {

    @Id
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;
    private int timeout;
    private String data;

}
